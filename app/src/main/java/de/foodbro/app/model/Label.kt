package de.foodbro.app.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "label_table",
    foreignKeys = [
        ForeignKey(entity = Recipe::class, parentColumns = ["id"], childColumns = ["recipeId"],
            onDelete = CASCADE)
    ]
)
data class Label(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    var name: String,

    // var color

    var recipeId: Long
)