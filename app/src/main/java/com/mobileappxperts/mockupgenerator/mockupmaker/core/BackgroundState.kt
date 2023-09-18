package com.mobileappxperts.mockupgenerator.mockupmaker.core

import androidx.compose.ui.graphics.Color
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.BackgroundModel

sealed class BackgroundState {
    data class Background(val backgroundModel: BackgroundModel?) : BackgroundState()
    data class BackgroundColor(val color: Color?) : BackgroundState()
    data class BackgroundGradient(val gradient: Int) : BackgroundState()
}