package de.foodbro.app.model

import androidx.room.Entity

@Entity(tableName = "unit_table")
enum class Units(val id: Long, private val abbreviation: String) {
    NONE(0, "None"),

    MILLIGRAM(1, "mg"),
    GRAM(2, "g"),
    KILOGRAM(3, "kg"),

    TEASPOON(4,"TL"),
    TABLESPOON(5, "EL"),

    MILLILITRE(6, "ml"),
    LITRE(7, "l"),

    CUP(8, "Tasse"),
    BUNCH(9, "Bund"),
    PINCH(10, "Prise"),

    CAN(11, "Dose"),
    PIECE(12, "Stück(e)"),
    PACKAGE(13, "Packung(en)"),
    SLICE(14, "Scheibe(n)"),

    SMALL(15, "klein"),
    MEDIUM(16, "mittel"),
    LARGE(17, "groß");

    override fun toString(): String {
        return abbreviation
    }

    companion object {
        @JvmStatic
        fun getValue(str: String): Units? = values().find { it.abbreviation == str }
    }
}