package de.foodbro.app.data.converter

import androidx.room.TypeConverter
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef

class StringListConverter {

    @TypeConverter
    fun toString(value: List<String>?): String? = jacksonObjectMapper().writeValueAsString(value)

    @TypeConverter
    fun toListOfStrings(value: String?): List<String>? =
        jacksonObjectMapper().readValue(value, jacksonTypeRef<List<String>>())
}