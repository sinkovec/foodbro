package de.foodbro.app.repository

import androidx.lifecycle.LiveData
import de.foodbro.app.database.IngredientDao
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.Recipe
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IngredientRepository
    @Inject constructor(private val ingredientDao: IngredientDao) {

    fun observeByRecipeId(id: Int): LiveData<List<Ingredient>> = ingredientDao.observeByRecipeId(id)

    suspend fun getByRecipeId(id: Int): List<Ingredient> = ingredientDao.getByRecipeId(id)

    fun insertAllForRecipe(recipe: Recipe, ingredients: List<Ingredient>) =
        ingredientDao.insertAllForRecipe(recipe, ingredients)
}