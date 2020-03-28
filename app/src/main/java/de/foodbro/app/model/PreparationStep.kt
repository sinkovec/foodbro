package de.foodbro.app.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "preparations_table")
data class PreparationStep @JvmOverloads constructor(
    var text: String = "",

    var pos: Int = 0,

    var recipeId: Long = 0,

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
) {
    fun printPosition() = "$pos."
}