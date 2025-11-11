package com.example.lab_week_10.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromTotalObject(totalObject: TotalObject): String {
        return Gson().toJson(totalObject)
    }

    @TypeConverter
    fun toTotalObject(json: String): TotalObject {
        val type = object : TypeToken<TotalObject>() {}.type
        return Gson().fromJson(json, type)
    }
}
