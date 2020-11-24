package de.foodbro.app.data

import androidx.lifecycle.LiveData
import androidx.room.*
import de.foodbro.app.model.Recipe
import de.foodbro.app.model.RecipeDetail

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe_table")
    fun getAll(): LiveData<List<Recipe>>

    @Transaction
    @Query("SELECT * FROM recipe_table WHERE id = :id")
    fun getById(id: Int): LiveData<RecipeDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: Recipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipeList: List<Recipe>)
}