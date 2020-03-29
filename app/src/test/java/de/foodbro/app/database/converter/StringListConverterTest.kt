package de.foodbro.app.database.converter

import org.junit.Assert.assertEquals
import org.junit.Test

class StringListConverterTest {

    private val converter = StringListConverter()

    @Test
    fun testToString() {
        assertEquals("[\"A\",\"B\",\"C\"]", converter.toString(listOf("A", "B", "C")))
    }

    @Test
    fun testToListOfStrings() {
        assertEquals(listOf("A", "B", "C"), converter.toListOfStrings("[\"A\", \"B\", \"C\"]"))
    }
}