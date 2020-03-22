package de.foodbro.app.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import de.foodbro.app.database.converter.UnitConverter

@Entity(tableName = "ingredient_table")
data class Ingredient(

    var name: String,

    var quantity: Int? = null,

    @TypeConverters(UnitConverter::class)
    var unit: Unit? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var recipeId: Int = 0
}