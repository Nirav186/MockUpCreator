package com.example.mockupscreenshots.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mockupscreenshots.data.converter.ListOfScreenshotsConverter
import com.example.mockupscreenshots.data.dao.ProjectDao
import com.example.mockupscreenshots.data.model.Project

@Database(
    entities = [Project::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(
    ListOfScreenshotsConverter::class
)
abstract class DatabaseHelper : RoomDatabase() {

    abstract fun getProjectDao(): ProjectDao

}