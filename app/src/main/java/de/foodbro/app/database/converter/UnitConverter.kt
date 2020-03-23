package de.foodbro.app.database.converter

import androidx.room.TypeConverter
import de.foodbro.app.model.Units

class UnitConverter {

    @TypeConverter
    fun toUnit(value: Int?): Units? = value?.let { enumValues<Units>()[value] }

    @TypeConverter
    fun toInt(unit: Units?): Int? = unit?.id
}