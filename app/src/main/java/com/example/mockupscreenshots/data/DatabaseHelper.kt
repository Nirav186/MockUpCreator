package com.example.mockupscreenshots.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mockupscreenshots.data.dao.ProjectDao
import com.example.mockupscreenshots.data.model.Project

@Database(
    entities = [Project::class],
    exportSchema = false,
    version = 1
)
abstract class DatabaseHelper : RoomDatabase() {

    abstract fun getProjectDao(): ProjectDao

}