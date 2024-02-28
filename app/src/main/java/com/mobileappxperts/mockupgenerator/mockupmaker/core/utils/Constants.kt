package com.mobileappxperts.mockupgenerator.mockupmaker.core.utils

import androidx.compose.ui.graphics.Color
import com.mobileappxperts.mockupgenerator.mockupmaker.core.BackgroundState
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.BackgroundModel

object Constants {

    const val MOCK_DATA_JSON_NAME = "device_frames.json"
    const val HOME_DATA_JSON_NAME = "home_frames.json"

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

    val bgs = listOf(
        BackgroundModel("bg1.png", 1),
        BackgroundModel("bg2.png", 1),
        BackgroundModel("bg3.png", 1),
        BackgroundModel("bg4.png", 1),
        BackgroundModel("bg5.png", 1),
        BackgroundModel("bg6.png", 1),
        BackgroundModel("bg7.png", 1),
        BackgroundModel("bg8.png", 1),
        BackgroundModel("bg9.png", 1),
        BackgroundModel("bg10.png", 1),
        BackgroundModel("bg11.png", 1),
        BackgroundModel("bg12.png", 1),
        BackgroundModel("bg13.png", 1),
        BackgroundModel("bg14.png", 1),
        BackgroundModel("bg15.png", 1),
        BackgroundModel("bg16.png", 1),
        BackgroundModel("bg17.png", 1),
        BackgroundModel("bg18.png", 1),
        BackgroundModel("bg19.png", 1),
        BackgroundModel("bg20.png", 1),
        BackgroundModel("bg21.png", 1),
        BackgroundModel("bg22.png", 1),
    )

    val gradients = listOf(
        "gr1",
        "gr2",
        "gr3",
        "gr4",
        "gr5",
        "gr6",
        "gr7",
        "gr8",
        "gr9",
        "gr10",
        "gr11",
        "gr12",
        "gr13",
        "gr14",
        "gr15",
        "gr16",
        "gr17",
        "gr18",
        "gr19",
        "gr20",
        "gr21",
        "gr22",
        "gr23",
        "gr24",
        "gr25",
        "gr26",
    )

    fun getRandomBackground(): BackgroundState {
        val list = listOf(0, 1, 2)
        return when (list.random()) {
            0 -> {
                val randomColor = bgColors.random()
                BackgroundState.BackgroundColor(randomColor)
            }

            1 -> {
                val randomBgs = bgs.random()
                BackgroundState.Background(randomBgs)
            }

            2 -> {
                val randomGradients = gradients.random()
                BackgroundState.BackgroundGradient(randomGradients)
            }

            else -> {
                val randomColor = bgColors.random()
                BackgroundState.BackgroundColor(randomColor)
            }
        }
    }

}