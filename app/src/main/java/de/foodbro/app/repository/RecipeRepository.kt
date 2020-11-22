package de.foodbro.app.repository

import de.foodbro.app.data.RecipeDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(private val recipeDao: RecipeDao) {

    fun getRecipes() = recipeDao.getRecipes()

    fun getRecipe(id: Int) = recipeDao.getRecipe(id)
}