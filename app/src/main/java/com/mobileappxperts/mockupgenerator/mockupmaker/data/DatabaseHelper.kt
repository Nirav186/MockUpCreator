package com.mobileappxperts.mockupgenerator.mockupmaker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mobileappxperts.mockupgenerator.mockupmaker.data.converter.ListOfScreenshotsConverter
import com.mobileappxperts.mockupgenerator.mockupmaker.data.dao.ProjectDao
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.Project

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