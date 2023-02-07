package com.example.mockupscreenshots.domain.usecase

import com.example.mockupscreenshots.core.Resource
import com.example.mockupscreenshots.core.UseCase
import com.example.mockupscreenshots.data.model.HomeFrame
import com.example.mockupscreenshots.domain.DeviceFrameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseGetHomeFramesFromAsset @Inject constructor(private val deviceFrameRepository: DeviceFrameRepository) :
    UseCase<Flow<Resource<List<HomeFrame>>>, Unit>() {
    override suspend fun action(params: Unit) = deviceFrameRepository.getHomeFramesFromAsset()
}