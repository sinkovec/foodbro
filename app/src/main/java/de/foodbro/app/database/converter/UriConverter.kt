package de.foodbro.app.database.converter

import android.net.Uri
import androidx.room.TypeConverter

class UriConverter {

    @TypeConverter
    fun toUri(value: String?): Uri? = value?.let { Uri.parse(value) }

    @TypeConverter
    fun toString(uri: Uri?): String? = uri?.toString()
}