package mockupmaker.screenshots.mockup.generator.data.repository

import com.google.gson.Gson
import mockupmaker.screenshots.mockup.generator.core.Resource
import mockupmaker.screenshots.mockup.generator.data.model.DeviceFrameItem
import mockupmaker.screenshots.mockup.generator.data.model.HomeFrame
import mockupmaker.screenshots.mockup.generator.domain.DeviceFrameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mockupmaker.screenshots.mockup.generator.App
import mockupmaker.screenshots.mockup.generator.core.utils.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceFrameRepositoryImpl @Inject constructor() : DeviceFrameRepository {

    override fun loadJSONFromAsset(): Flow<Resource<List<DeviceFrameItem>>> {
        return flow {
            emit(Resource.Loading(true))
            val jsonString = App.appContext.assets.open(Constants.MOCK_DATA_JSON_NAME)
                .bufferedReader().use {
                it.readText()
            }
            val gson = Gson()
            val `object`: Array<DeviceFrameItem> =
                gson.fromJson(jsonString, Array<DeviceFrameItem>::class.java)
            val myObjects: ArrayList<DeviceFrameItem> = ArrayList(`object`.toList())
            emit(Resource.Success(myObjects))
        }
    }

    override fun getHomeFramesFromAsset(): Flow<Resource<List<HomeFrame>>> {
        return flow {
            emit(Resource.Loading(true))
            val jsonString = App.appContext.assets.open(Constants.HOME_DATA_JSON_NAME)
                .bufferedReader().use {
                    it.readText()
                }
            val gson = Gson()
            val `object`: Array<HomeFrame> =
                gson.fromJson(jsonString, Array<HomeFrame>::class.java)
            val myObjects: ArrayList<HomeFrame> = ArrayList(`object`.toList())
            emit(Resource.Success(myObjects))
        }
    }
}