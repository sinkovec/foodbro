package de.foodbro.app.repository

import androidx.lifecycle.LiveData
import de.foodbro.app.database.RecipeDao
import de.foodbro.app.model.Recipe
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository
    @Inject constructor(private val recipeDao: RecipeDao) {

    fun getAll(): LiveData<List<Recipe>> = recipeDao.getAll()

    fun getById(id: Int): LiveData<Recipe> = recipeDao.getById(id)

    fun insert(recipe: Recipe) = recipeDao.insert(recipe)
}