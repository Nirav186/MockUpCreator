package com.example.mockupscreenshots.core.utils

import androidx.compose.ui.graphics.Color

object Constants {

    val bgColors = listOf(
        ColorPicker(Color(0xFF000000), Color.White),
        ColorPicker(Color(0xFF606c38), Color.White),
        ColorPicker(Color(0xFFfefae0), Color.Black),
        ColorPicker(Color(0xFFdda15e), Color.White),
        ColorPicker(Color(0xFFbc6c25), Color.White),
        ColorPicker(Color(0xFFcdb4db), Color.Black),
        ColorPicker(Color(0xFFffc8dd), Color.Black),
        ColorPicker(Color(0xFFffafcc), Color.Black),
        ColorPicker(Color(0xFFbde0fe), Color.Black),
        ColorPicker(Color(0xFFa2d2ff), Color.Black),
        ColorPicker(Color(0xFF8665C0), Color.White),
        ColorPicker(Color(0xFF2c6e49), Color.White),
        ColorPicker(Color(0xFF4c956c), Color.White),
        ColorPicker(Color(0xFF355070), Color.White),
        ColorPicker(Color(0xFF6d597a), Color.White),
        ColorPicker(Color(0xFFb56576), Color.White),
        ColorPicker(Color(0xFFe56b6f), Color.White),
        ColorPicker(Color(0xFF403d39), Color.White),
        ColorPicker(Color(0xFF5f0f40), Color.White),
        ColorPicker(Color(0xFF9a031e), Color.White),
        ColorPicker(Color(0xFF0f4c5c), Color.White),
        ColorPicker(Color(0xFF47387a), Color.White),
        ColorPicker(Color(0xFFcd036d), Color.White),
        ColorPicker(Color(0xFF735751), Color.White),
        ColorPicker(Color(0xFFb75d69), Color.White),
        ColorPicker(Color(0xFF05668d), Color.White),
    )

    var isInterReadyToShow = true
    const val MOCK_DATA_JSON_NAME = "device_frames.json"
    const val HOME_DATA_JSON_NAME = "home_frames.json"

}

data class ColorPicker(
    val primaryColor: Color,
    val secondaryColor: Color
)