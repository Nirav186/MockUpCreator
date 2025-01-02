package mockupmaker.screenshots.mockup.generator.data.dao

import androidx.room.*
import mockupmaker.screenshots.mockup.generator.data.model.Project
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewProject(project: Project): Long

    @Query("SELECT * FROM Projects WHERE projectId is :projectId")
    fun getProjectById(projectId: Long): Flow<Project>

    @Query("select * from Projects")
    fun getAllProjects(): Flow<List<Project>>

    @Query("delete from Projects where projectId in (:mIds)")
    fun deleteProjects(mIds:ArrayList<Long>)

}