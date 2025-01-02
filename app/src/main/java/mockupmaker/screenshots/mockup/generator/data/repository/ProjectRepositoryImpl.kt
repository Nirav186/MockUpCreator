package mockupmaker.screenshots.mockup.generator.data.repository

import mockupmaker.screenshots.mockup.generator.data.dao.ProjectDao
import mockupmaker.screenshots.mockup.generator.data.model.Project
import mockupmaker.screenshots.mockup.generator.domain.ProjectRepository
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