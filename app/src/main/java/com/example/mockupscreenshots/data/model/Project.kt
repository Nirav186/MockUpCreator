package com.example.mockupscreenshots.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Projects")
data class Project(
    @PrimaryKey(autoGenerate = true) @SerializedName("projectId") val projectId: Long = 0L,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("device") val device: String,
    @SerializedName("screenshots") val screenshots: List<String>
)
