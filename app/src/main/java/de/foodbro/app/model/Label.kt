package de.foodbro.app.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "label_table")
data class Label(
    var name: String = "",

    // var color

    var recipeId: Long = 0,

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
)