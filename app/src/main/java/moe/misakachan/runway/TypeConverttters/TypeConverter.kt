package moe.misakachan.runway.TypeConverttters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter {
    @TypeConverter
    fun toString(weekDays: Array<Boolean>): String? {
        val gson = Gson()
        return gson.toJson(weekDays)
    }

    @TypeConverter
    fun toWeekDays(array: String): Array<Boolean> {
        val gson = Gson()
        return gson.fromJson(array, Array<Boolean>::class.java)
    }

    @TypeConverter
    fun toString(stuff: ArrayList<String>): String? {
        val gson = Gson()
        return gson.toJson(stuff)
    }

    @TypeConverter
    fun toStuff(array: String): ArrayList<String> {
        val gson = Gson()
        return gson.fromJson(array, object : TypeToken<ArrayList<String>>() {}.type)
    }
}