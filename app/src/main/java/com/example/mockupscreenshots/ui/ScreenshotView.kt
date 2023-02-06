package com.example.mockupscreenshots.ui

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ScreenshotView @JvmOverloads constructor(
    val modifier: Modifier,
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    val title: MutableState<String>,
    val subTitle: MutableState<String>,
    val deviceFrameView: MutableState<DeviceFrameView>,
    val bitmap: MutableState<Bitmap>,
    val selectedBgColor: MutableState<Color>,
    val selectedBg: MutableState<Int?>,
    val textColor: MutableState<Color>
) : AbstractComposeView(context, attributeSet, defStyleAttr) {

    @Composable
    override fun Content() {
        ScreenShotView()
    }

    @Composable
    private fun ScreenShotView() {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            selectedBg.value?.let {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = it),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = if (selectedBg.value != null) {
                            Color.Transparent
                        } else selectedBgColor.value
                    ),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    text = title.value,
                    style = TextStyle(
                        color = textColor.value,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    text = subTitle.value,
                    style = TextStyle(
                        color = textColor.value,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                if (deviceFrameView.value.width > 0 && deviceFrameView.value.height > 0) {
                    Image(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 10.dp),
                        bitmap = bitmap.value.asImageBitmap(),
                        contentDescription = null
                    )
                }
            }
        }
    }


}