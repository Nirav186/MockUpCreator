package mockupmaker.screenshots.mockup.generator.domain

import mockupmaker.screenshots.mockup.generator.core.Resource
import mockupmaker.screenshots.mockup.generator.data.model.DeviceFrameItem
import mockupmaker.screenshots.mockup.generator.data.model.HomeFrame
import kotlinx.coroutines.flow.Flow

interface DeviceFrameRepository {
    fun loadJSONFromAsset(): Flow<Resource<List<DeviceFrameItem>>>
    fun getHomeFramesFromAsset(): Flow<Resource<List<HomeFrame>>>
}