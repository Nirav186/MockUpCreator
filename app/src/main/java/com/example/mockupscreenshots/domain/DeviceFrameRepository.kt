package com.example.mockupscreenshots.domain

import com.example.mockupscreenshots.core.Resource
import com.example.mockupscreenshots.data.model.DeviceFrameItem
import kotlinx.coroutines.flow.Flow

interface DeviceFrameRepository {
    fun loadJSONFromAsset(): Flow<Resource<List<DeviceFrameItem>>>
}