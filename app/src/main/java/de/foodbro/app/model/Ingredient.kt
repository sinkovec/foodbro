package de.foodbro.app.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import de.foodbro.app.data.converter.UnitsConverter

@Entity(
    tableName = "ingredient_table",
    foreignKeys = [
        ForeignKey(entity = Recipe::class, parentColumns = ["id"], childColumns = ["recipeId"],
            onDelete = CASCADE)
    ]
)
data class Ingredient(
    @PrimaryKey(autoGenerate = true)
    var id : Int,

    var name : String,

    var quantity : Int,

    @TypeConverters(UnitsConverter::class)
    var unit : Units,

    var recipeId : Int
)

