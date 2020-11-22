package de.foodbro.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "recipe_table"
)
data class Recipe constructor(
    @PrimaryKey(autoGenerate = true)
    val recipeId : Int,

    val name : String,
)