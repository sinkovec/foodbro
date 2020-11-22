package de.foodbro.app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe_table")
    fun getRecipes(): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipe_table WHERE recipeId == :id")
    fun getRecipe(id: Int): LiveData<Recipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipeList: List<Recipe>)
}