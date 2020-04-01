package de.foodbro.app.model

import android.net.Uri
import androidx.room.*
import de.foodbro.app.database.converter.IntPairConverter
import de.foodbro.app.database.converter.StringListConverter
import de.foodbro.app.database.converter.UriConverter
import java.util.*

@Entity(
    tableName = "recipe_table",
    indices = [Index("id")]
)
data class Recipe @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    var name: String = "",

    var description: String = "",

    @TypeConverters(UriConverter::class)
    var imageUri: Uri? = null,

    var portions: Int? = null,

    var difficulty: Int? = null,

    @TypeConverters(IntPairConverter::class)
    var preparationTime: Pair<Int,Int>? = Pair(0, 30) // 30 minutes default prep time
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