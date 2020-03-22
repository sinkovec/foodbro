package de.foodbro.app.database.converter

import androidx.room.TypeConverter
import de.foodbro.app.model.Unit

class UnitConverter {

    @TypeConverter
    fun toUnit(value: Int?): Unit? = value?.let { enumValues<Unit>()[value] }

    @TypeConverter
    fun toInt(unit: Unit?): Int? = unit?.id
}