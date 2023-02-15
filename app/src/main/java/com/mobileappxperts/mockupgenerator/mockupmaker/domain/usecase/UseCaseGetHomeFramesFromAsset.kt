package com.mobileappxperts.mockupgenerator.mockupmaker.domain.usecase

import com.mobileappxperts.mockupgenerator.mockupmaker.core.Resource
import com.mobileappxperts.mockupgenerator.mockupmaker.core.UseCase
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.HomeFrame
import com.mobileappxperts.mockupgenerator.mockupmaker.domain.DeviceFrameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseGetHomeFramesFromAsset @Inject constructor(private val deviceFrameRepository: DeviceFrameRepository) :
    UseCase<Flow<Resource<List<HomeFrame>>>, Unit>() {
    override suspend fun action(params: Unit) = deviceFrameRepository.getHomeFramesFromAsset()
}