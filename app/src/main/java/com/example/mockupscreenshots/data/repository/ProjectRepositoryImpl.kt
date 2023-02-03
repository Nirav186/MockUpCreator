package com.example.mockupscreenshots.data.repository

import com.example.mockupscreenshots.data.dao.ProjectDao
import com.example.mockupscreenshots.data.model.Project
import com.example.mockupscreenshots.domain.ProjectRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRepositoryImpl @Inject constructor(
    private val projectDao: ProjectDao
) : ProjectRepository {
    override suspend fun addNewProject(project: Project) = projectDao.addNewProject(project)
    override fun getProjectById(projectId: Long) = projectDao.getProjectById(projectId)
    override fun getMyProjects() = projectDao.getAllProjects()
}