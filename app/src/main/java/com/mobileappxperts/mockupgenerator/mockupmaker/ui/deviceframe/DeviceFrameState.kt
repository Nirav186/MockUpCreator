package com.mobileappxperts.mockupgenerator.mockupmaker.ui.deviceframe

import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.DeviceFrameItem
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.HomeFrame

data class DeviceFrameState(
    var frameItems: List<DeviceFrameItem> = listOf(),
    var homeFrames: List<HomeFrame> = listOf(),
    val isLoading: Boolean = false,
)