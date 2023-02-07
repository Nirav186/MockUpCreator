package com.example.mockupscreenshots.domain

import com.example.mockupscreenshots.core.Resource
import com.example.mockupscreenshots.data.model.DeviceFrameItem
import com.example.mockupscreenshots.data.model.HomeFrame
import kotlinx.coroutines.flow.Flow

interface DeviceFrameRepository {
    fun loadJSONFromAsset(): Flow<Resource<List<DeviceFrameItem>>>
    fun getHomeFramesFromAsset(): Flow<Resource<List<HomeFrame>>>
}