package de.foodbro.app.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "preparations_table",
    foreignKeys = [
        ForeignKey(entity = Recipe::class, parentColumns = ["id"], childColumns = ["recipeId"],
            onDelete = ForeignKey.CASCADE)
    ]
)
data class PreparationStep @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    var text: String = "",

    var pos: Int = 0,

    var recipeId: Long = 0
) {
    fun printPosition() = "$pos."
}