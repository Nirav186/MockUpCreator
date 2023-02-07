package com.example.mockupscreenshots.ui

import com.example.mockupscreenshots.data.model.DeviceFrameItem
import com.example.mockupscreenshots.data.model.HomeFrame

data class DeviceFrameState(
    var frameItems: List<DeviceFrameItem> = listOf(),
    var homeFrames: List<HomeFrame> = listOf(),
    val isLoading: Boolean = false,
)