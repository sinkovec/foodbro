package de.foodbro.app.repository

import android.util.Log
import androidx.lifecycle.LiveData
import de.foodbro.app.database.IngredientDao
import de.foodbro.app.database.PreparationDao
import de.foodbro.app.database.RecipeDao
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.PreparationStep
import de.foodbro.app.model.Recipe
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeDetailRepository
    @Inject constructor(private val recipeDao: RecipeDao,
                        private val ingredientDao: IngredientDao,
                        private val preparationDao: PreparationDao) {

    fun observeRecipeById(id: Long): LiveData<Recipe> = recipeDao.observeById(id)

    fun observeIngredientsByRecipeId(id: Long): LiveData<List<Ingredient>> = ingredientDao.observeByRecipeId(id)

    fun observePreparationStepsByRecipeId(id: Long): LiveData<List<PreparationStep>> = preparationDao.observeByRecipeId(id)

    suspend fun getRecipeById(id: Long): Recipe? = recipeDao.getById(id)

    suspend fun getIngredientsByRecipeId(id: Long): List<Ingredient> = ingredientDao.getByRecipeId(id)

    suspend fun getPreparationStepsByRecipeId(id: Long): List<PreparationStep> = preparationDao.getByRecipeId(id)

    suspend fun insert(recipe: Recipe, ingredients: List<Ingredient>, preparation: List<PreparationStep>) {
        val id = recipeDao.insert(recipe)
        ingredientDao.insertByRecipeId(id, ingredients)
        preparationDao.insertByRecipeId(id, preparation)
    }
}