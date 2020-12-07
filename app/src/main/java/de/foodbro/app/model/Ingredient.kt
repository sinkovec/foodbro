package de.foodbro.app.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import de.foodbro.app.data.converter.UnitsConverter

@Entity(
    tableName = "ingredient_table",
    indices = [Index("id")],
    foreignKeys = [
        ForeignKey(entity = Recipe::class, parentColumns = ["id"], childColumns = ["recipeId"],
            onDelete = CASCADE, onUpdate = CASCADE)
    ]
)
data class Ingredient(
    @PrimaryKey(autoGenerate = true)
    var id : Long,

    var name : String,

    var quantity : Int,

    @TypeConverters(UnitsConverter::class)
    var unit : Units,

    var recipeId : Long
)

