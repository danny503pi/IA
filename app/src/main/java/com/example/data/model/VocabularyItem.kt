package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad que representa una palabra o frase en Náhuat.
 * Almacena la expresión en Náhuat, traducción en Español, pronunciación fonética,
 * categoría, ejemplo ilustrativo y el estado de guardado (favorito / mazo de repaso).
 */
@Entity(tableName = "vocabulary")
data class VocabularyItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nahuat: String,
    val spanish: String,
    val phonetic: String,
    val category: String,
    val exampleNahuat: String,
    val exampleSpanish: String,
    val isFavorite: Boolean = false
)
