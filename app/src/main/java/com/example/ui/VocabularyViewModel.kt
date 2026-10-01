package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.InitialVocabularyData
import com.example.data.model.VocabularyItem
import com.example.data.repository.VocabularyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Pestañas principales de navegación dentro de Náhuat Vivo.
 */
enum class AppTab(val title: String) {
    CATALOG("Catálogo"),
    FAVORITES("Favoritos"),
    FLASHCARDS("Tarjetas de Repaso")
}

/**
 * Estado para la sesión interactiva de tarjetas de repaso (Flashcards).
 */
data class FlashcardSessionState(
    val cards: List<VocabularyItem> = emptyList(),
    val currentIndex: Int = 0,
    val isFlipped: Boolean = false,
    val knownCount: Int = 0,
    val reviewCount: Int = 0,
    val isFinished: Boolean = false,
    val isPracticingAll: Boolean = false
) {
    val currentCard: VocabularyItem?
        get() = cards.getOrNull(currentIndex)
    val totalCards: Int
        get() = cards.size
    val progress: Float
        get() = if (totalCards > 0) (currentIndex.toFloat() / totalCards.toFloat()) else 0f
}

/**
 * ViewModel que gestiona la lógica de búsqueda, filtrado, favoritos y sesión de flashcards.
 */
class VocabularyViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: VocabularyRepository

    // Pestaña actual seleccionada
    private val _currentTab = MutableStateFlow(AppTab.CATALOG)
    val currentTab: StateFlow<AppTab> = _currentTab

    // Texto de búsqueda
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    // Categoría seleccionada para filtrar
    private val _selectedCategory = MutableStateFlow("Todas")
    val selectedCategory: StateFlow<String> = _selectedCategory

    // Categorías disponibles
    val categories: List<String> = InitialVocabularyData.categories

    // Flujo de todas las palabras desde la base de datos
    val allWords: StateFlow<List<VocabularyItem>>

    // Flujo de palabras marcadas como favoritas
    val favoriteWords: StateFlow<List<VocabularyItem>>

    // Palabras filtradas para el catálogo principal
    val filteredCatalog: StateFlow<List<VocabularyItem>>

    // Palabras filtradas para la sección de favoritos
    val filteredFavorites: StateFlow<List<VocabularyItem>>

    // Estado del mazo de flashcards
    private val _flashcardState = MutableStateFlow(FlashcardSessionState())
    val flashcardState: StateFlow<FlashcardSessionState> = _flashcardState

    init {
        val database = AppDatabase.getDatabase(application)
        repository = VocabularyRepository(database.vocabularyDao())

        // Aseguramos que la base de datos tenga los datos iniciales
        viewModelScope.launch {
            repository.checkAndSeedInitialData()
        }

        allWords = repository.allWords.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = InitialVocabularyData.items
        )

        favoriteWords = repository.favoriteWords.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = InitialVocabularyData.items.filter { it.isFavorite }
        )

        // Combinación reactiva para catálogo
        filteredCatalog = combine(allWords, _searchQuery, _selectedCategory) { words, query, category ->
            words.filter { item ->
                val matchesCategory = (category == "Todas" || item.category.equals(category, ignoreCase = true))
                val q = query.trim().lowercase()
                val matchesQuery = q.isEmpty() ||
                        item.nahuat.lowercase().contains(q) ||
                        item.spanish.lowercase().contains(q) ||
                        item.phonetic.lowercase().contains(q)
                matchesCategory && matchesQuery
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = InitialVocabularyData.items
        )

        // Combinación reactiva para favoritos
        filteredFavorites = combine(favoriteWords, _searchQuery) { favs, query ->
            val q = query.trim().lowercase()
            if (q.isEmpty()) favs else {
                favs.filter { item ->
                    item.nahuat.lowercase().contains(q) ||
                            item.spanish.lowercase().contains(q) ||
                            item.phonetic.lowercase().contains(q)
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = InitialVocabularyData.items.filter { it.isFavorite }
        )

        // Inicializar las tarjetas cuando haya palabras favoritas
        viewModelScope.launch {
            favoriteWords.collect { favorites ->
                val currentState = _flashcardState.value
                // Si el mazo de flashcards está vacío y no se configuró modo "todas las palabras"
                if (currentState.cards.isEmpty() && !currentState.isPracticingAll) {
                    if (favorites.isNotEmpty()) {
                        _flashcardState.value = FlashcardSessionState(
                            cards = favorites,
                            currentIndex = 0,
                            isFlipped = false,
                            isPracticingAll = false
                        )
                    }
                }
            }
        }
    }

    // --- Acciones de Búsqueda y Navegación ---

    fun onSearchQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun onCategorySelected(category: String) {
        _selectedCategory.value = category
    }

    fun setTab(tab: AppTab) {
        _currentTab.value = tab
        // Si el usuario entra a Flashcards y no hay sesión activa, inicializar con favoritos o catálogo
        if (tab == AppTab.FLASHCARDS) {
            initFlashcardDeckIfNeeded()
        }
    }

    fun clearSearch() {
        _searchQuery.value = ""
    }

    // --- Acción de Favoritos / Repaso ---

    fun toggleFavorite(item: VocabularyItem) {
        viewModelScope.launch {
            repository.toggleFavorite(item)
        }
    }

    // --- Acciones de Flashcards / Tarjetas de Repaso ---

    private fun initFlashcardDeckIfNeeded() {
        val currentState = _flashcardState.value
        if (currentState.cards.isEmpty() || currentState.isFinished) {
            val favs = favoriteWords.value
            val sourceCards = if (favs.isNotEmpty()) favs else allWords.value
            _flashcardState.value = FlashcardSessionState(
                cards = sourceCards,
                currentIndex = 0,
                isFlipped = false,
                knownCount = 0,
                reviewCount = 0,
                isFinished = false,
                isPracticingAll = favs.isEmpty()
            )
        }
    }

    fun startDeck(practiceAll: Boolean) {
        val sourceCards = if (practiceAll) allWords.value else {
            val favs = favoriteWords.value
            if (favs.isNotEmpty()) favs else allWords.value
        }
        _flashcardState.value = FlashcardSessionState(
            cards = sourceCards,
            currentIndex = 0,
            isFlipped = false,
            knownCount = 0,
            reviewCount = 0,
            isFinished = false,
            isPracticingAll = practiceAll
        )
    }

    fun flipCard() {
        _flashcardState.value = _flashcardState.value.copy(
            isFlipped = !_flashcardState.value.isFlipped
        )
    }

    fun nextCard() {
        val current = _flashcardState.value
        if (current.currentIndex + 1 < current.totalCards) {
            _flashcardState.value = current.copy(
                currentIndex = current.currentIndex + 1,
                isFlipped = false
            )
        } else {
            _flashcardState.value = current.copy(isFinished = true)
        }
    }

    fun previousCard() {
        val current = _flashcardState.value
        if (current.currentIndex > 0) {
            _flashcardState.value = current.copy(
                currentIndex = current.currentIndex - 1,
                isFlipped = false
            )
        }
    }

    fun markCardKnown() {
        val current = _flashcardState.value
        val newKnown = current.knownCount + 1
        if (current.currentIndex + 1 < current.totalCards) {
            _flashcardState.value = current.copy(
                currentIndex = current.currentIndex + 1,
                isFlipped = false,
                knownCount = newKnown
            )
        } else {
            _flashcardState.value = current.copy(
                knownCount = newKnown,
                isFinished = true
            )
        }
    }

    fun markCardForReview() {
        val current = _flashcardState.value
        val newReview = current.reviewCount + 1
        // Asegurar que quede marcada como favorita para repasar luego
        current.currentCard?.let { card ->
            if (!card.isFavorite) {
                viewModelScope.launch {
                    repository.setFavorite(card.id, true)
                }
            }
        }

        if (current.currentIndex + 1 < current.totalCards) {
            _flashcardState.value = current.copy(
                currentIndex = current.currentIndex + 1,
                isFlipped = false,
                reviewCount = newReview
            )
        } else {
            _flashcardState.value = current.copy(
                reviewCount = newReview,
                isFinished = true
            )
        }
    }

    fun shuffleDeck() {
        val current = _flashcardState.value
        if (current.cards.isNotEmpty()) {
            _flashcardState.value = current.copy(
                cards = current.cards.shuffled(),
                currentIndex = 0,
                isFlipped = false,
                isFinished = false
            )
        }
    }

    fun restartDeck() {
        val current = _flashcardState.value
        _flashcardState.value = current.copy(
            currentIndex = 0,
            isFlipped = false,
            knownCount = 0,
            reviewCount = 0,
            isFinished = false
        )
    }
}
