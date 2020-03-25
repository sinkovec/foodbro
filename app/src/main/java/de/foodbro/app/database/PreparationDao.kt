package de.foodbro.app.database

import androidx.lifecycle.LiveData
import androidx.room.*
import de.foodbro.app.model.PreparationStep

@Dao
interface PreparationDao {

    @Query("SELECT * FROM preparations_table WHERE recipeId = :id")
    fun observeByRecipeId(id: Long): LiveData<List<PreparationStep>>

    @Query("SELECT * FROM preparations_table WHERE recipeId = :id")
    suspend fun getByRecipeId(id: Long): List<PreparationStep>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg preparationStep: PreparationStep)

    suspend fun insertByRecipeId(recipeId: Long, preparationSteps: List<PreparationStep>) {
        preparationSteps.map {
            it.recipeId = recipeId
        }
        insert(*preparationSteps.toTypedArray())
    }

    @Query("DELETE FROM preparations_table")
    fun deleteAll()

    @Query("DELETE FROM preparations_table WHERE recipeId = :id")
    fun deleteAllByRecipeId(id: Long)

    @Delete
    fun delete(ingredient: PreparationStep)
}