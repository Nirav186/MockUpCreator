package com.mobileappxperts.mockupgenerator.mockupmaker.data.model


import com.google.gson.annotations.SerializedName

data class HomeFrame(
    @SerializedName("background") val background: String?,
    @SerializedName("backgroundGradient") val backgroundGradient: Int?,
    @SerializedName("backgroundColor") val backgroundColor: String?,
    @SerializedName("frameId") val frameId: String,
    @SerializedName("id") val id: Int,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("textColor") val textColor: Int
)