package de.foodbro.app.database.converter

import org.junit.Assert
import org.junit.Test

class IntPairConverterTest {

    private val converter = IntPairConverter()

    @Test
    fun testToString() {
        Assert.assertEquals("{\"first\":1,\"second\":2}", converter.toString(Pair(1, 2)))
    }

    @Test
    fun testToPairOfInt() {
        Assert.assertEquals(Pair(1, 2), converter.toPairOfInt("{\"first\":1,\"second\":2}"))
    }
}