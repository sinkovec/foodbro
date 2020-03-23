package de.foodbro.app.repository

import androidx.lifecycle.LiveData
import de.foodbro.app.database.RecipeDao
import de.foodbro.app.model.Recipe
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository
    @Inject constructor(private val recipeDao: RecipeDao) {

    fun observeAll(): LiveData<List<Recipe>> = recipeDao.observeAll()

    fun observeById(id: Int): LiveData<Recipe> = recipeDao.observeById(id)

    suspend fun getById(id: Int): Recipe? = recipeDao.getById(id)

    suspend fun insert(recipe: Recipe) = recipeDao.insert(recipe)
}