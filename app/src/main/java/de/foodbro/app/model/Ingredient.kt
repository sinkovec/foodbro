package de.foodbro.app.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import de.foodbro.app.database.converter.UnitConverter

@Entity(tableName = "ingredient_table")
data class Ingredient(
    var name: String = "",

    var quantity: Int? = null,

    @TypeConverters(UnitConverter::class)
    var unit: Units? = null,

    var recipeId: Long = 0,

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
) {
    fun printQuantityAndUnit() = listOfNotNull(quantity, unit).joinToString(" ")

    override fun toString() = listOfNotNull(quantity, unit, name).joinToString(" ")
}