package com.example.mockupscreenshots.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mockupscreenshots.data.model.Project

@Dao
interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewProject(project: Project): Long

    @Query("select * from Projects")
    suspend fun getAllProjects():List<Project>
}