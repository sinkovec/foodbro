package de.foodbro.app.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import de.foodbro.app.data.converter.StringListConverter

@Entity(
    tableName = "recipe_table",
    indices = [Index("id")]
)
data class Recipe constructor(
    @PrimaryKey(autoGenerate = true)
    var id : Long,

    var name : String,

    @TypeConverters(StringListConverter::class)
    var preparationSteps : List<String>
)