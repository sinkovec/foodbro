package de.foodbro.app.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import de.foodbro.app.database.converter.StringListConverter

@Entity(tableName = "recipe_table")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    var name: String,

    var description: String,

    var portions: Int,

    @TypeConverters(StringListConverter::class)
    var preparation: List<String>
)