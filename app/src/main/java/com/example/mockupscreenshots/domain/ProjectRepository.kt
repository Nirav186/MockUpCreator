package com.example.mockupscreenshots.domain

import com.example.mockupscreenshots.data.model.Project
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {
    suspend fun addNewProject(project: Project): Long
    fun getProjectById(projectId: Long): Flow<Project>
    fun getMyProjects(): Flow<List<Project>>
    fun deleteProjects(mIds:ArrayList<Long>)
}