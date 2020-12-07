package de.foodbro.app.repository

import de.foodbro.app.data.IngredientDao
import de.foodbro.app.data.RecipeDao
import de.foodbro.app.model.RecipeDetail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(
    private val recipeDao: RecipeDao,
    private val ingredientDao: IngredientDao
) {

    fun getRecipes() = recipeDao.getAll()

    fun getRecipe(id: Long) = recipeDao.getById(id)

    suspend fun insert(recipeDetail: RecipeDetail) {
        val id = recipeDao.insert(recipeDetail.recipe)
        recipeDetail.ingredients.forEach { it.recipeId = id }
        ingredientDao.insertAll(recipeDetail.ingredients)
    }
}