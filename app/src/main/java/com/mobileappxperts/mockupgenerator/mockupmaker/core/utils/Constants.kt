package com.mobileappxperts.mockupgenerator.mockupmaker.core.utils

import androidx.compose.ui.graphics.Color
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.BackgroundModel
import com.mobileappxperts.mockupgenerator.mockupmaker.R

object Constants {

//    val bgColors = listOf(
//        ColorPicker(Color(0xFF000000), Color.White),
//        ColorPicker(Color(0xFF606c38), Color.White),
//        ColorPicker(Color(0xFFfefae0), Color.Black),
//        ColorPicker(Color(0xFFdda15e), Color.White),
//        ColorPicker(Color(0xFFbc6c25), Color.White),
//        ColorPicker(Color(0xFFcdb4db), Color.Black),
//        ColorPicker(Color(0xFFffc8dd), Color.Black),
//        ColorPicker(Color(0xFFffafcc), Color.Black),
//        ColorPicker(Color(0xFFbde0fe), Color.Black),
//        ColorPicker(Color(0xFFa2d2ff), Color.Black),
//        ColorPicker(Color(0xFF8665C0), Color.White),
//        ColorPicker(Color(0xFF2c6e49), Color.White),
//        ColorPicker(Color(0xFF4c956c), Color.White),
//        ColorPicker(Color(0xFF355070), Color.White),
//        ColorPicker(Color(0xFF6d597a), Color.White),
//        ColorPicker(Color(0xFFb56576), Color.White),
//        ColorPicker(Color(0xFFe56b6f), Color.White),
//        ColorPicker(Color(0xFF403d39), Color.White),
//        ColorPicker(Color(0xFF5f0f40), Color.White),
//        ColorPicker(Color(0xFF9a031e), Color.White),
//        ColorPicker(Color(0xFF0f4c5c), Color.White),
//        ColorPicker(Color(0xFF47387a), Color.White),
//        ColorPicker(Color(0xFFcd036d), Color.White),
//        ColorPicker(Color(0xFF735751), Color.White),
//        ColorPicker(Color(0xFFb75d69), Color.White),
//        ColorPicker(Color(0xFF05668d), Color.White),
//    )

    val bgColors = listOf(
        Color(0xFF000000),
        Color(0xFF606c38),
        Color(0xFFfefae0),
        Color(0xFFdda15e),
        Color(0xFFbc6c25),
        Color(0xFFcdb4db),
        Color(0xFFffc8dd),
        Color(0xFFffafcc),
        Color(0xFFbde0fe),
        Color(0xFFa2d2ff),
        Color(0xFF8665C0),
        Color(0xFF2c6e49),
        Color(0xFF4c956c),
        Color(0xFF355070),
        Color(0xFF6d597a),
        Color(0xFFb56576),
        Color(0xFFe56b6f),
        Color(0xFF403d39),
        Color(0xFF5f0f40),
        Color(0xFF9a031e),
        Color(0xFF0f4c5c),
        Color(0xFF47387a),
        Color(0xFFcd036d),
        Color(0xFF735751),
        Color(0xFFb75d69),
        Color(0xFF05668d),
    )

    var isInterReadyToShow = true
    const val MOCK_DATA_JSON_NAME = "device_frames.json"
    const val HOME_DATA_JSON_NAME = "home_frames.json"

    val bgs = listOf(
        BackgroundModel("bg1.png",1),
        BackgroundModel("bg2.png",1),
        BackgroundModel("bg3.png",1),
        BackgroundModel("bg4.png",1),
        BackgroundModel("bg5.png",1),
        BackgroundModel("bg6.png",1),
        BackgroundModel("bg7.png",1),
        BackgroundModel("bg8.png",1),
        BackgroundModel("bg9.png",1),
        BackgroundModel("bg10.png",1),
        BackgroundModel("bg11.png",1),
        BackgroundModel("bg12.png",1),
        BackgroundModel("bg13.png",1),
        BackgroundModel("bg14.png",1),
        BackgroundModel("bg15.png",1),
        BackgroundModel("bg16.png",1),
        BackgroundModel("bg17.png",1),
        BackgroundModel("bg18.png",1),
        BackgroundModel("bg19.png",1),
        BackgroundModel("bg20.png",1),
        BackgroundModel("bg21.png",1),
        BackgroundModel("bg22.png",1),
    )

    val gradients = listOf(
        R.drawable.gr1,
        R.drawable.gr2,
        R.drawable.gr3,
        R.drawable.gr4,
        R.drawable.gr5,
        R.drawable.gr6,
        R.drawable.gr7,
        R.drawable.gr8,
        R.drawable.gr9,
        R.drawable.gr10,
        R.drawable.gr11,
        R.drawable.gr12,
        R.drawable.gr13,
        R.drawable.gr14,
        R.drawable.gr15,
        R.drawable.gr16,
        R.drawable.gr17,
        R.drawable.gr18,
        R.drawable.gr19,
        R.drawable.gr20,
        R.drawable.gr21,
        R.drawable.gr22,
        R.drawable.gr23,
        R.drawable.gr24,
        R.drawable.gr25,
        R.drawable.gr26,
    )

}



data class ColorPicker(
    val primaryColor: Color,
    val secondaryColor: Color
)