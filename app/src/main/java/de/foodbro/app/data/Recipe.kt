package de.foodbro.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import de.foodbro.app.data.converter.StringListConverter

@Entity(
    tableName = "recipe_table"
)
data class Recipe constructor(
    @PrimaryKey(autoGenerate = true)
    var recipeId : Int,

    var name : String,

    @TypeConverters(StringListConverter::class)
    var ingredients : List<String>,

    @TypeConverters(StringListConverter::class)
    var preparationSteps : List<String>,
)