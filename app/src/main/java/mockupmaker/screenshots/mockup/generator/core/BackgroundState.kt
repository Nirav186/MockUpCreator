package mockupmaker.screenshots.mockup.generator.core

import androidx.compose.ui.graphics.Color
import mockupmaker.screenshots.mockup.generator.data.model.BackgroundModel

sealed class BackgroundState {
    data class Background(val backgroundModel: BackgroundModel?) : BackgroundState()
    data class BackgroundColor(val color: Color?) : BackgroundState()
    data class BackgroundGradient(val gradient: String) : BackgroundState()
}