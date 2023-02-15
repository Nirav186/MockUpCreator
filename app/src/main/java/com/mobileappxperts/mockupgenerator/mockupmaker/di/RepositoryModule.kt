package com.mobileappxperts.mockupgenerator.mockupmaker.di

import com.mobileappxperts.mockupgenerator.mockupmaker.data.repository.DeviceFrameRepositoryImpl
import com.mobileappxperts.mockupgenerator.mockupmaker.data.repository.ProjectRepositoryImpl
import com.mobileappxperts.mockupgenerator.mockupmaker.domain.DeviceFrameRepository
import com.mobileappxperts.mockupgenerator.mockupmaker.domain.ProjectRepository
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