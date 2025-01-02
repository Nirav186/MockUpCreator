package mockupmaker.screenshots.mockup.generator.data.converter

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ListOfScreenshotsConverter {

    @TypeConverter
    fun toString(value: SnapshotStateList<String>?): String? {
        val gson = Gson()
        val type = object : TypeToken<SnapshotStateList<String>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toList(value: String?): SnapshotStateList<String>? {
        val gson = Gson()
        val type = object : TypeToken<SnapshotStateList<String>>() {}.type
        return gson.fromJson(value, type)
    }
}