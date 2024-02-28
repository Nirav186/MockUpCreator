package mockupmaker.screenshots.mockup.generator.di

import android.content.Context
import androidx.room.Room
import mockupmaker.screenshots.mockup.generator.data.DatabaseHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabaseHelper(@ApplicationContext appContext: Context): DatabaseHelper =
        Room.databaseBuilder(
            appContext,
            DatabaseHelper::class.java, "MockupDatabase"
        ).build()

    @Provides
    @Singleton
    fun providesProjectDao(databaseHelper: DatabaseHelper) = databaseHelper.getProjectDao()
}