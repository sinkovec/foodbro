package de.foodbro.app.model

import androidx.room.Embedded
import androidx.room.Relation

data class RecipeDetail(
    @Embedded
    val recipe: Recipe,

    @Relation(parentColumn = "id", entityColumn = "recipeId", entity = Ingredient::class)
    val ingredients: MutableList<Ingredient>,
)