package de.foodbro.app.database

import androidx.room.*
import de.foodbro.app.model.Ingredient

@Dao
interface IngredientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg ingredient: Ingredient)
}