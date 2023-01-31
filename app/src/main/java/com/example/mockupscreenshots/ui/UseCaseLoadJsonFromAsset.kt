package com.example.mockupscreenshots.ui

import com.example.mockupscreenshots.core.Resource
import com.example.mockupscreenshots.core.UseCase
import com.example.mockupscreenshots.data.model.DeviceFrameItem
import com.example.mockupscreenshots.domain.DeviceFrameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseLoadJsonFromAsset @Inject constructor(private val deviceFrameRepository: DeviceFrameRepository) :
    UseCase<Flow<Resource<List<DeviceFrameItem>>>, Unit>() {
    override suspend fun action(params: Unit) = deviceFrameRepository.loadJSONFromAsset()
}