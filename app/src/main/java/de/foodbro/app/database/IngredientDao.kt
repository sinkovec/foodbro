package de.foodbro.app.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.*
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.Recipe

@Dao
interface IngredientDao {

    @Query("SELECT * FROM ingredient_table WHERE recipeId = :id")
    fun observeByRecipeId(id: Long): LiveData<List<Ingredient>>

    @Query("SELECT * FROM ingredient_table WHERE recipeId = :id")
    suspend fun getByRecipeId(id: Long): List<Ingredient>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg ingredient: Ingredient)

    suspend fun insertByRecipeId(recipeId: Long, ingredients: List<Ingredient>) {
        ingredients.map {
            it.recipeId = recipeId
        }
        insert(*ingredients.toTypedArray())
    }

    @Query("DELETE FROM ingredient_table")
    fun deleteAll()

    @Query("DELETE FROM ingredient_table WHERE recipeId = :id")
    fun deleteAllByRecipeId(id: Long)

    @Delete
    fun delete(ingredient: Ingredient)
}