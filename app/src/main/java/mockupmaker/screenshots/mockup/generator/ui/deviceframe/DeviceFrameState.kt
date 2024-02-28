package mockupmaker.screenshots.mockup.generator.ui.deviceframe

import mockupmaker.screenshots.mockup.generator.data.model.DeviceFrameItem
import mockupmaker.screenshots.mockup.generator.data.model.HomeFrame

data class DeviceFrameState(
    var frameItems: List<DeviceFrameItem> = listOf(),
    var homeFrames: List<HomeFrame> = listOf(),
    val isLoading: Boolean = false,
)