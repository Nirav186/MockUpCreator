package com.example.mockupscreenshots.domain

import com.example.mockupscreenshots.data.model.Project

interface ProjectRepository {
    suspend fun addNewProject(project: Project): Long
    suspend fun getMyProjects(): List<Project>
}