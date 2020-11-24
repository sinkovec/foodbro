package de.foodbro.app.model

enum class Units(
    var abbreviation : String
) {
    NONE("None"),

    MILLIGRAM("mg"),
    GRAM("g"),
    KILOGRAM("kg"),

    TEASPOON("TL"),
    TABLESPOON("EL"),

    MILLILITRE("ml"),
    LITRE("l"),

    CUP("Tasse"),
    BUNCH("Bund"),
    PINCH("Prise"),

    CAN("Dose"),
    PIECE("Stück(e)"),
    PACKAGE("Packung(en)"),
    SLICE("Scheibe(n)"),

    SMALL("klein"),
    MEDIUM("mittel"),
    LARGE("groß");

    override fun toString(): String {
        return abbreviation
    }
}