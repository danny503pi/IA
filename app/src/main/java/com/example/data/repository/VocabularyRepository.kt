package com.example.data.repository

import com.example.data.local.InitialVocabularyData
import com.example.data.local.VocabularyDao
import com.example.data.model.VocabularyItem
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio para la gestión de palabras de Náhuat y favoritos.
 * Abstrae el acceso a datos para la capa de presentación (ViewModel).
 */
class VocabularyRepository(private val dao: VocabularyDao) {

    val allWords: Flow<List<VocabularyItem>> = dao.getAllWords()
    val favoriteWords: Flow<List<VocabularyItem>> = dao.getFavoriteWords()

    suspend fun checkAndSeedInitialData() {
        val count = dao.getCount()
        if (count == 0) {
            dao.insertAll(InitialVocabularyData.items)
        }
    }

    suspend fun toggleFavorite(item: VocabularyItem) {
        dao.updateFavorite(item.id, !item.isFavorite)
    }

    suspend fun setFavorite(id: Int, isFavorite: Boolean) {
        dao.updateFavorite(id, isFavorite)
    }
}
