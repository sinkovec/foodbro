package de.foodbro.app.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
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

    fun observeRecipeById(id: Int): LiveData<Recipe> = recipeDao.observeById(id)

    fun observeIngredientsByRecipeId(id: Int): LiveData<List<Ingredient>> = ingredientDao.observeByRecipeId(id)

    fun observePreparationStepsByRecipeId(id: Int): LiveData<List<PreparationStep>> = preparationDao.observeByRecipeId(id)

    suspend fun getRecipeById(id: Int): Recipe? = recipeDao.getById(id)

    suspend fun getIngredientsByRecipeId(id: Int): List<Ingredient> = ingredientDao.getByRecipeId(id)

    suspend fun getPreparationStepsByRecipeId(id: Int): List<PreparationStep> = preparationDao.getByRecipeId(id)

    suspend fun insert(recipe: Recipe) = recipeDao.insert(recipe)

    suspend fun insertIngredients(recipeId: Int, ingredients: List<Ingredient>) = ingredientDao.insertAllForRecipe(recipeId, ingredients)

    suspend fun insertPreparation(recipeId: Int, preparationSteps: List<PreparationStep>) = preparationDao.insertAllForRecipe(recipeId, preparationSteps)
}