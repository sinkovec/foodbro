package de.foodbro.app.database

import androidx.lifecycle.LiveData
import androidx.room.*
import de.foodbro.app.model.PreparationStep

@Dao
interface PreparationDao {

    @Query("SELECT * FROM preparations_table WHERE recipeId = :id")
    fun observeByRecipeId(id: Int): LiveData<List<PreparationStep>>

    @Query("SELECT * FROM preparations_table WHERE recipeId = :id")
    suspend fun getByRecipeId(id: Int): List<PreparationStep>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(ingredients: List<PreparationStep>)

    suspend fun insertAllForRecipe(recipeId: Int, preparationSteps: List<PreparationStep>) {
        preparationSteps.forEach {
            it.recipeId = recipeId
        }
        insertAll(preparationSteps)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ingredient: PreparationStep)

    @Query("DELETE FROM preparations_table")
    fun deleteAll()

    @Query("DELETE FROM preparations_table WHERE recipeId = :id")
    fun deleteAllByRecipeId(id: Int)

    @Delete
    fun delete(ingredient: PreparationStep)
}