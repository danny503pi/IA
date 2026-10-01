package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.VocabularyItem
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) para la persistencia local del vocabulario.
 * Retorna Flow para actualizaciones reactivas e inmediatas en la UI de Compose.
 */
@Dao
interface VocabularyDao {

    @Query("SELECT * FROM vocabulary ORDER BY nahuat ASC")
    fun getAllWords(): Flow<List<VocabularyItem>>

    @Query("SELECT * FROM vocabulary WHERE isFavorite = 1 ORDER BY nahuat ASC")
    fun getFavoriteWords(): Flow<List<VocabularyItem>>

    @Query("SELECT * FROM vocabulary WHERE category = :category ORDER BY nahuat ASC")
    fun getWordsByCategory(category: String): Flow<List<VocabularyItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(words: List<VocabularyItem>)

    @Query("UPDATE vocabulary SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavorite(id: Int, isFavorite: Boolean)

    @Query("SELECT COUNT(*) FROM vocabulary")
    suspend fun getCount(): Int
}
