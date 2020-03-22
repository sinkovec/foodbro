package de.foodbro.app.database

import androidx.lifecycle.LiveData
import androidx.room.*
import de.foodbro.app.model.Recipe

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe_table")
    fun getAll(): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipe_table WHERE id = :id")
    fun getById(id: Int): LiveData<Recipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recipe: Recipe): Long

    @Query("DELETE FROM recipe_table")
    fun deleteAll()

    @Delete
    fun delete(recipe: Recipe)
}