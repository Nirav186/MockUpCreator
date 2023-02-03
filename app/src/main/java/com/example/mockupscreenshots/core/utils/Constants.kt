package com.example.mockupscreenshots.core.utils

import androidx.compose.ui.graphics.Color

object Constants {

    val bgColors = listOf(
//        ColorPicker(Color(0xFFFFFFFF), Color.Black),
//        ColorPicker(Color(0xFFBFBFBF), Color.Black),
        ColorPicker(Color(0xFF000000), Color.White),
//        ColorPicker(Color(0xFFFBCFC6), Color.Black),
//        ColorPicker(Color(0xFFFFF6BD), Color.Black),
//        ColorPicker(Color(0xFFD0F2E0), Color.Black),
//        ColorPicker(Color(0xFFC7EAFE), Color.Black),
//        ColorPicker(Color(0xFFE7CBFE), Color.Black),
//        ColorPicker(Color(0xFFED6E79), Color.Black),
//        ColorPicker(Color(0xFFFFD35E), Color.Black),
//        ColorPicker(Color(0xFF91BF74), Color.Black),
//        ColorPicker(Color(0xFF7F95A0), Color.Black),
        ColorPicker(Color(0xFF8665C0), Color.White),
        ColorPicker(Color(0xFFAE1B2B), Color.White),
        ColorPicker(Color(0xFF6C8A68), Color.White),
        ColorPicker(Color(0xFF3C6D98), Color.White),
        ColorPicker(Color(0xFF061E3E), Color.White),
        ColorPicker(Color(0xFF251E3E), Color.White),
        ColorPicker(Color(0xFF451E3E), Color.White),
        ColorPicker(Color(0xFF651E3E), Color.White),
        ColorPicker(Color(0xFF851E3E), Color.White),
        ColorPicker(Color(0xFF343434), Color.White),
        ColorPicker(Color(0xFF0F421F), Color.White),
        ColorPicker(Color(0xFF790000), Color.White),
        ColorPicker(Color(0xFF3C2F2F), Color.White),
        ColorPicker(Color(0xFF854442), Color.White),
        ColorPicker(Color(0xFF4B3832), Color.White),
    )

}

data class ColorPicker(
    val primaryColor: Color,
    val secondaryColor: Color
)