package com.example.mockupscreenshots.data.model


import com.google.gson.annotations.SerializedName

data class HomeFrame(
    @SerializedName("backgroundColor") val backgroundColor: String,
    @SerializedName("frameId") val frameId: String,
    @SerializedName("id") val id: Int,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("textColor") val textColor: Int
)