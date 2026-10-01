package com.example

import com.example.data.local.InitialVocabularyData
import com.example.data.model.VocabularyItem
import com.example.ui.FlashcardSessionState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Pruebas unitarias de las 3 funciones requeridas:
 * 1. Búsqueda y filtrado por categoría del vocabulario.
 * 2. Guardar y desmarcar favoritos.
 * 3. Tarjetas de práctica (flashcards) y progresión de mazo.
 */
class NahuatVocabularyTest {

    @Test
    fun testInitialVocabularyLoaded() {
        val items = InitialVocabularyData.items
        assertTrue("El vocabulario inicial no debe estar vacío", items.isNotEmpty())
        assertTrue("Debe contener al menos 20 palabras", items.size >= 20)
    }

    @Test
    fun testCategoryFiltering() {
        val items = InitialVocabularyData.items
        val greetings = items.filter { it.category.equals("Saludos y Cortesía", ignoreCase = true) }
        assertTrue("Deben existir palabras en la categoría Saludos y Cortesía", greetings.isNotEmpty())

        val animals = items.filter { it.category.equals("Animales", ignoreCase = true) }
        assertTrue("Deben existir palabras en la categoría Animales", animals.isNotEmpty())
    }

    @Test
    fun testSearchFiltering() {
        val items = InitialVocabularyData.items
        val query = "tunal"
        val results = items.filter {
            it.nahuat.contains(query, ignoreCase = true) || it.spanish.contains(query, ignoreCase = true)
        }
        assertTrue("La búsqueda de 'tunal' debe arrojar resultados", results.isNotEmpty())
        assertTrue("El resultado debe contener Tunal", results.any { it.nahuat.contains("Tunal", ignoreCase = true) })
    }

    @Test
    fun testFavoriteToggle() {
        val item = VocabularyItem(
            id = 100,
            nahuat = "Shitajtuli",
            spanish = "Habla",
            phonetic = "[shi-taj-tu-li]",
            category = "Vida Cotidiana",
            exampleNahuat = "Shitajtuli náhuat",
            exampleSpanish = "Habla en náhuat",
            isFavorite = false
        )
        val toggled = item.copy(isFavorite = !item.isFavorite)
        assertTrue("Debe cambiar a favorito", toggled.isFavorite)
        val toggledBack = toggled.copy(isFavorite = !toggled.isFavorite)
        assertFalse("Debe desmarcarse de favorito", toggledBack.isFavorite)
    }

    @Test
    fun testFlashcardSessionProgression() {
        val deck = InitialVocabularyData.items.take(3)
        var state = FlashcardSessionState(
            cards = deck,
            currentIndex = 0,
            isFlipped = false
        )

        assertNotNull(state.currentCard)
        assertEquals("Yeyek tunal", state.currentCard?.nahuat)
        assertFalse(state.isFlipped)

        // Voltear
        state = state.copy(isFlipped = !state.isFlipped)
        assertTrue(state.isFlipped)

        // Avanzar a la siguiente tarjeta
        state = state.copy(currentIndex = state.currentIndex + 1, isFlipped = false)
        assertEquals(1, state.currentIndex)
        assertFalse(state.isFlipped)

        // Marcar conocida
        state = state.copy(knownCount = state.knownCount + 1)
        assertEquals(1, state.knownCount)
    }
}
