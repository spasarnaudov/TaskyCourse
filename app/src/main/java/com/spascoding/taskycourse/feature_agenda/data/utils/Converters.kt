package com.spascoding.taskycourse.feature_agenda.data.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.spascoding.taskycourse.feature_agenda.data.local.event.Photo

class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromPhotoList(value: List<Photo>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toPhotoList(value: String): List<Photo> {
        val listType = object : TypeToken<List<Photo>>() {}.type
        return Gson().fromJson(value, listType)
    }
}