package de.foodbro.app.database.converter

import androidx.room.TypeConverter
import de.foodbro.app.model.Units

class UnitConverter {

    @TypeConverter
    fun toUnit(value: Long?): Units? = value?.let { enumValues<Units>()[it.toInt()] }

    @TypeConverter
    fun toLong(unit: Units?): Long? = unit?.id
}