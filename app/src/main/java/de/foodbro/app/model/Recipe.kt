package de.foodbro.app.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import de.foodbro.app.database.converter.IntPairConverter
import de.foodbro.app.database.converter.StringListConverter
import java.util.*

@Entity(tableName = "recipe_table")
data class Recipe @JvmOverloads constructor(
    var name: String = "",

    var description: String = "",

    var portions: Int? = null,

    var difficulty: Int? = null,

    @TypeConverters(IntPairConverter::class)
    var preparationTime: Pair<Int,Int>? = Pair(0, 30),

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
) {
    fun printPreparationTime(): String {
        var res = ""
        if (preparationTime?.first != 0) {
            res += "${preparationTime?.first} h "
        }
        if (preparationTime?.second != 0) {
            res += "${preparationTime?.second} min"
        }
        return res.ifBlank { "0 min" }
    }
}