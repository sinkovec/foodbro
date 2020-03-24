package de.foodbro.app.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import de.foodbro.app.database.converter.StringListConverter
import java.util.*

@Entity(tableName = "recipe_table")
data class Recipe @JvmOverloads constructor(
    var name: String = "",

    var description: String = "",

    var portions: Int? = null,

    var difficulty: Int? = null,

    var preparationTime: Int? = null,

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)