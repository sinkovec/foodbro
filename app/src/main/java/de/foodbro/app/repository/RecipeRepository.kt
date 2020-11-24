package de.foodbro.app.repository

import de.foodbro.app.data.IngredientDao
import de.foodbro.app.model.Recipe
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

    fun getRecipe(id: Int) = recipeDao.getById(id)

    suspend fun insert(recipeDetail: RecipeDetail) {
        recipeDao.insert(recipeDetail.recipe)
        recipeDetail.ingredients.forEach {
            it.recipeId = recipeDetail.recipe.id
        }
        ingredientDao.insertAll(recipeDetail.ingredients)

    }
}