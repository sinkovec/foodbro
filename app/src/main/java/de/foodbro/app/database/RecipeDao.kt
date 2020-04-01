package de.foodbro.app.database

import androidx.lifecycle.LiveData
import androidx.room.*
import de.foodbro.app.model.Recipe
import de.foodbro.app.model.RecipeDetail

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe_table")
    fun observeAll(): LiveData<List<Recipe>>

    @Transaction
    @Query("SELECT * FROM recipe_table WHERE id = :id")
    fun observeById(id: Long): LiveData<RecipeDetail>

    @Transaction
    @Query("SELECT * FROM recipe_table WHERE id = :id")
    suspend fun getById(id: Long): RecipeDetail?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: Recipe): Long

    @Delete
    fun delete(recipe: Recipe)

    @Query("DELETE FROM recipe_table")
    fun deleteAll()
}