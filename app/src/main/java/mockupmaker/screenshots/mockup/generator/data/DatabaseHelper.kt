package mockupmaker.screenshots.mockup.generator.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import mockupmaker.screenshots.mockup.generator.data.converter.ListOfScreenshotsConverter
import mockupmaker.screenshots.mockup.generator.data.dao.ProjectDao
import mockupmaker.screenshots.mockup.generator.data.model.Project

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