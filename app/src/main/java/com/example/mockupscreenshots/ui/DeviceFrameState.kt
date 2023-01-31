package com.example.mockupscreenshots.ui

import com.example.mockupscreenshots.data.model.DeviceFrameItem

data class DeviceFrameState(
    var frameItems: List<DeviceFrameItem> = listOf(),
    val isLoading: Boolean = false,
)