package de.foodbro.app.model

import androidx.room.Entity

@Entity(tableName = "unit_table")
enum class Units(val id: Int, private val abbreviation: String) {
    MILLIGRAM(0, "mg"),
    GRAM(1,"g"),
    KILOGRAM(2, "kg"),

    TEASPOON(3, "TL"),
    TABLESPOON(4, "EL"),

    MILLILITRE(5, "ml"),
    LITRE(6, "l"),

    CUP(7, "Tasse"),
    BUNCH(8, "Bund"),
    PINCH(9, "Prise"),

    CAN(10, "Dose"),
    PIECE(11, "Stück(e)"),
    PACKAGE(12, "Packung(en)"),
    SLICE(13, "Scheibe(n)"),

    SMALL(14, "klein"),
    MEDIUM(15, "mittel"),
    LARGE(16, "groß");

    override fun toString(): String {
        return abbreviation
    }

    companion object {
        @JvmStatic
        fun getValue(str: String): Units? = values().find { it.abbreviation == str }
    }
}