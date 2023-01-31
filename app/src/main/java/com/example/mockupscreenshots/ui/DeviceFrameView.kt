package com.example.mockupscreenshots.ui

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mockupscreenshots.R
import com.example.mockupscreenshots.core.getImageFromAsset
import com.example.mockupscreenshots.data.model.DeviceFrameItem

class DeviceFrameView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    val frame: DeviceFrameItem,
    val bitmap: MutableState<Bitmap>?
) : AbstractComposeView(context, attributeSet, defStyleAttr) {

    @Composable
    override fun Content() {
        DeviceFrame(frame = frame)
    }

    @Composable
    fun DeviceFrame(frame: DeviceFrameItem) {
        Box(
            modifier = Modifier
                .width((frame.width).dp)
                .height((frame.height).dp)
        ) {
            bitmap?.value?.let {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(frame.getPadding()),
                    bitmap = it.asImageBitmap(),
                    contentDescription = "ScreenShot",
                    contentScale = ContentScale.FillBounds
                )
            }
            context.getImageFromAsset(frame.frameId)?.let {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    bitmap = it,
                    contentDescription = "Frame",
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}