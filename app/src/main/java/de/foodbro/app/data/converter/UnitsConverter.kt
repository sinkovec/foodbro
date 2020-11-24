package de.foodbro.app.data.converter

import androidx.room.TypeConverter
import de.foodbro.app.model.Units

class UnitsConverter {

    @TypeConverter
    fun toUnit(value: Int?): Units? = value?.let { enumValues<Units>()[it] }

    @TypeConverter
    fun toInt(unit: Units?): Int? = unit?.ordinal
}