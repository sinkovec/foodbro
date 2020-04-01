package de.foodbro.app.repository

import androidx.lifecycle.LiveData
import de.foodbro.app.database.IngredientDao
import de.foodbro.app.database.PreparationDao
import de.foodbro.app.database.RecipeDao
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.PreparationStep
import de.foodbro.app.model.Recipe
import de.foodbro.app.model.RecipeDetail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeDetailRepository
    @Inject constructor(private val recipeDao: RecipeDao,
                        private val ingredientDao: IngredientDao,
                        private val preparationDao: PreparationDao) {

    fun observeById(id: Long): LiveData<RecipeDetail> = recipeDao.observeById(id)

    suspend fun getById(id: Long): RecipeDetail? = recipeDao.getById(id)

    suspend fun insert(recipeDetail: RecipeDetail): Long {
        val id = recipeDao.insert(recipeDetail.recipe)
        recipeDetail.ingredients.forEach { it.apply { it.recipeId = id } }
        recipeDetail.preparationSteps.forEach { it.apply { it.recipeId = id } }
        ingredientDao.insert(*recipeDetail.ingredients.toTypedArray())
        preparationDao.insert(*recipeDetail.preparationSteps.toTypedArray())
        return id
    }
}