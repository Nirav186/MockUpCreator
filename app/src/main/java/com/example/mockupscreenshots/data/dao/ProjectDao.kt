package com.example.mockupscreenshots.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mockupscreenshots.data.model.Project
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewProject(project: Project): Long

    @Query("SELECT * FROM Projects WHERE projectId is :projectId")
    fun getProjectById(projectId: Long): Flow<Project>

    @Query("select * from Projects")
    fun getAllProjects(): Flow<List<Project>>
}