package com.mobileappxperts.mockupgenerator.mockupmaker.domain

import com.mobileappxperts.mockupgenerator.mockupmaker.core.Resource
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.DeviceFrameItem
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.HomeFrame
import kotlinx.coroutines.flow.Flow

interface DeviceFrameRepository {
    fun loadJSONFromAsset(): Flow<Resource<List<DeviceFrameItem>>>
    fun getHomeFramesFromAsset(): Flow<Resource<List<HomeFrame>>>
}