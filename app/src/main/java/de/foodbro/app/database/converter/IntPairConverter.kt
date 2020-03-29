package de.foodbro.app.database.converter

import androidx.room.TypeConverter
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef

class IntPairConverter {

    @TypeConverter
    fun toString(value: Pair<Int,Int>?): String? = jacksonObjectMapper().writeValueAsString(value)

    @TypeConverter
    fun toPairOfInt(value: String?): Pair<Int,Int>? =
        jacksonObjectMapper().readValue(value, jacksonTypeRef<Pair<Int,Int>>())
}