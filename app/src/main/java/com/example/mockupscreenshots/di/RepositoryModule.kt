package com.example.mockupscreenshots.di

import com.example.mockupscreenshots.data.repository.DeviceFrameRepositoryImpl
import com.example.mockupscreenshots.data.repository.ProjectRepositoryImpl
import com.example.mockupscreenshots.domain.DeviceFrameRepository
import com.example.mockupscreenshots.domain.ProjectRepository
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