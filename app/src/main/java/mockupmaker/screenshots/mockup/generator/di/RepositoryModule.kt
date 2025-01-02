package mockupmaker.screenshots.mockup.generator.di

import mockupmaker.screenshots.mockup.generator.data.repository.DeviceFrameRepositoryImpl
import mockupmaker.screenshots.mockup.generator.data.repository.ProjectRepositoryImpl
import mockupmaker.screenshots.mockup.generator.domain.DeviceFrameRepository
import mockupmaker.screenshots.mockup.generator.domain.ProjectRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class RepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindDeviceFrameRepository(repository: DeviceFrameRepositoryImpl): DeviceFrameRepository

    @ActivityRetainedScoped
    @Binds
    abstract fun bindProjectRepository(repository: ProjectRepositoryImpl): ProjectRepository
}