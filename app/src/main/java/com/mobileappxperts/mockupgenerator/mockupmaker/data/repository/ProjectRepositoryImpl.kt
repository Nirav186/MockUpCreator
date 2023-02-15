package com.mobileappxperts.mockupgenerator.mockupmaker.data.repository

import com.mobileappxperts.mockupgenerator.mockupmaker.data.dao.ProjectDao
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.Project
import com.mobileappxperts.mockupgenerator.mockupmaker.domain.ProjectRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRepositoryImpl @Inject constructor(
    private val projectDao: ProjectDao
) : ProjectRepository {
    override suspend fun addNewProject(project: Project) = projectDao.addNewProject(project)
    override fun getProjectById(projectId: Long) = projectDao.getProjectById(projectId)
    override fun getMyProjects() = projectDao.getAllProjects()
    override fun deleteProjects(mIds:ArrayList<Long>) = projectDao.deleteProjects(mIds)
}