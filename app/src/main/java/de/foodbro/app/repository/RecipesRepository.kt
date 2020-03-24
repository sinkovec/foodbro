package de.foodbro.app.repository

import androidx.lifecycle.LiveData
import de.foodbro.app.database.RecipeDao
import de.foodbro.app.model.Recipe
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipesRepository
    @Inject constructor(private val recipeDao: RecipeDao) {

    fun observeAll(): LiveData<List<Recipe>> = recipeDao.observeAll()
}