package com.mobileappxperts.mockupgenerator.mockupmaker.data.repository

import com.google.gson.Gson
import com.mobileappxperts.mockupgenerator.mockupmaker.App
import com.mobileappxperts.mockupgenerator.mockupmaker.core.Resource
import com.mobileappxperts.mockupgenerator.mockupmaker.core.utils.Constants
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.DeviceFrameItem
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.HomeFrame
import com.mobileappxperts.mockupgenerator.mockupmaker.domain.DeviceFrameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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