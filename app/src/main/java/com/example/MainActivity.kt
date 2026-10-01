package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.AppTab
import com.example.ui.VocabularyViewModel
import com.example.ui.components.AppTopBar
import com.example.ui.components.BottomNavBar
import com.example.ui.screens.CatalogScreen
import com.example.ui.screens.FavoritesScreen
import com.example.ui.screens.FlashcardsScreen
import com.example.ui.theme.MyApplicationTheme

/**
 * Actividad principal de Náhuat Vivo.
 * Controla el contenedor Scaffold con barra superior, navegación inferior
 * y alternancia fluida entre las 3 funciones principales:
 * 1. Buscador/Catálogo con filtro por categoría
 * 2. Lista de Favoritos para repaso
 * 3. Tarjetas interactivas de práctica (Flashcards)
 */
class MainActivity : ComponentActivity() {

    private val viewModel: VocabularyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                NahuatVivoApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun NahuatVivoApp(viewModel: VocabularyViewModel) {
    val currentTab by viewModel.currentTab.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()
    val filteredCatalog by viewModel.filteredCatalog.collectAsStateWithLifecycle()
    val favoriteWords by viewModel.favoriteWords.collectAsStateWithLifecycle()
    val flashcardState by viewModel.flashcardState.collectAsStateWithLifecycle()

    // Manejo de botón Atrás en Android para regresar al catálogo desde pestañas secundarias
    if (currentTab != AppTab.CATALOG) {
        BackHandler {
            viewModel.setTab(AppTab.CATALOG)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                title = "Náhuat Vivo",
                subtitle = when (currentTab) {
                    AppTab.CATALOG -> "Catálogo y Buscador Bilingüe"
                    AppTab.FAVORITES -> "Palabras para Repaso (${favoriteWords.size})"
                    AppTab.FLASHCARDS -> "Tarjetas de Práctica Interactiva"
                }
            )
        },
        bottomBar = {
            BottomNavBar(
                currentTab = currentTab,
                onTabSelected = { viewModel.setTab(it) },
                favoriteCount = favoriteWords.size
            )
        }
    ) { innerPadding ->
        when (currentTab) {
            AppTab.CATALOG -> {
                CatalogScreen(
                    words = filteredCatalog,
                    categories = viewModel.categories,
                    selectedCategory = selectedCategory,
                    searchQuery = searchQuery,
                    onSearchQueryChange = { viewModel.onSearchQueryChanged(it) },
                    onCategorySelected = { viewModel.onCategorySelected(it) },
                    onClearSearch = { viewModel.clearSearch() },
                    onToggleFavorite = { viewModel.toggleFavorite(it) },
                    modifier = Modifier.padding(innerPadding)
                )
            }
            AppTab.FAVORITES -> {
                FavoritesScreen(
                    favoriteWords = favoriteWords,
                    onToggleFavorite = { viewModel.toggleFavorite(it) },
                    onStartPractice = {
                        viewModel.startDeck(practiceAll = false)
                        viewModel.setTab(AppTab.FLASHCARDS)
                    },
                    onGoToCatalog = { viewModel.setTab(AppTab.CATALOG) },
                    modifier = Modifier.padding(innerPadding)
                )
            }
            AppTab.FLASHCARDS -> {
                FlashcardsScreen(
                    sessionState = flashcardState,
                    onFlipCard = { viewModel.flipCard() },
                    onNextCard = { viewModel.nextCard() },
                    onPreviousCard = { viewModel.previousCard() },
                    onMarkKnown = { viewModel.markCardKnown() },
                    onMarkReview = { viewModel.markCardForReview() },
                    onShuffle = { viewModel.shuffleDeck() },
                    onRestart = { viewModel.restartDeck() },
                    onPracticeAll = { viewModel.startDeck(practiceAll = true) },
                    onPracticeFavorites = { viewModel.startDeck(practiceAll = false) },
                    hasFavorites = favoriteWords.isNotEmpty(),
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}
