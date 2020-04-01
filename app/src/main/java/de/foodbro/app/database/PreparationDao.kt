package de.foodbro.app.database

import androidx.room.*
import de.foodbro.app.model.PreparationStep

@Dao
interface PreparationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg preparationStep: PreparationStep)
}