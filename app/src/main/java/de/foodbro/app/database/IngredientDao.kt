package de.foodbro.app.database

import androidx.lifecycle.LiveData
import androidx.room.*
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.Recipe

@Dao
interface IngredientDao {

    @Query("SELECT * FROM ingredient_table WHERE recipeId = :id")
    fun observeByRecipeId(id: Int): LiveData<List<Ingredient>>

    @Query("SELECT * FROM ingredient_table WHERE recipeId = :id")
    suspend fun getByRecipeId(id: Int): List<Ingredient>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(ingredients: List<Ingredient>)

    suspend fun insertAllForRecipe(recipeId: Int, ingredients: List<Ingredient>) {
        ingredients.forEach {
            it.recipeId = recipeId
        }
        insertAll(ingredients)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ingredient: Ingredient)

    @Query("DELETE FROM ingredient_table")
    fun deleteAll()

    @Query("DELETE FROM ingredient_table WHERE recipeId = :id")
    fun deleteAllByRecipeId(id: Int)

    @Delete
    fun delete(ingredient: Ingredient)
}