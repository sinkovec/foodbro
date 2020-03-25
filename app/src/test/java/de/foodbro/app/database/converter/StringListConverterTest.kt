package de.foodbro.app.database.converter

import org.junit.Assert.assertEquals
import org.junit.Test

class StringListConverterTest {

    @Test
    fun testToString() {
        assertEquals("[\"A\",\"B\",\"C\"]", StringListConverter().toString(listOf("A", "B", "C")))
    }

    @Test
    fun testToListOfStrings() {
        assertEquals(listOf("A", "B", "C"), StringListConverter().toListOfStrings("[\"A\", \"B\", \"C\"]"))
    }
}