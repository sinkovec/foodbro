package de.foodbro.app.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import de.foodbro.app.database.converter.UnitConverter

@Entity(
    tableName = "ingredient_table",
    foreignKeys = [
        ForeignKey(entity = Recipe::class, parentColumns = ["id"], childColumns = ["recipeId"],
            onDelete = CASCADE)
    ]
)
data class Ingredient @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    var name: String = "",

    var quantity: Int? = null,

    @TypeConverters(UnitConverter::class)
    var unit: Units? = null,

    var recipeId: Long = 0
) {

    fun printQuantityAndUnit() = listOfNotNull(quantity, unit).joinToString(" ")

    override fun toString() = listOfNotNull(quantity, unit, name).joinToString(" ")
}