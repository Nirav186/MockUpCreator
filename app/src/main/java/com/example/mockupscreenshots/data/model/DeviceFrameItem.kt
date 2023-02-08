package com.example.mockupscreenshots.data.model


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import com.google.gson.annotations.SerializedName

data class DeviceFrameItem(
    @SerializedName("deviceName") val deviceName: String,
    @SerializedName("deviceType") val deviceType: String,
    @SerializedName("frameId") val frameId: String,
    @SerializedName("frameUrl") val frameUrl: String,
    @SerializedName("height") val height: Double,
    @SerializedName("id") val id: Int,
    @SerializedName("originalHeight") val originalHeight: Int,
    @SerializedName("originalWidth") val originalWidth: Int,
    @SerializedName("paddingBottom") val paddingBottom: Int,
    @SerializedName("paddingEnd") val paddingEnd: Int,
    @SerializedName("paddingStart") val paddingStart: Int,
    @SerializedName("paddingTop") val paddingTop: Int,
    @SerializedName("width") val width: Double
):java.io.Serializable {
    fun getPadding() = PaddingValues(
        start = (paddingStart).dp,
        end = (paddingEnd).dp,
        top = (paddingTop).dp,
        bottom = (paddingBottom).dp
    )
}