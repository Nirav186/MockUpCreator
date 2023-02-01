package com.example.mockupscreenshots.ui

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mockupscreenshots.R

class ScreenshotView @JvmOverloads constructor(
    val modifier: Modifier,
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    val title: MutableState<String>,
    val subTitle: MutableState<String>,
    val deviceFrameView: MutableState<DeviceFrameView>,
    val imageBitmap: MutableState<ImageBitmap>,
    val bitmap: MutableState<Bitmap>
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
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.dog_paws_green),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Column {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    text = title.value,
                    style = TextStyle(
                        color = Color.White,
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
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.height(30.dp))
                if (deviceFrameView.value.width > 0 && deviceFrameView.value.height > 0) {
                    Image(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        bitmap = bitmap.value.asImageBitmap(),
                        contentDescription = null
                    )
                }
            }
        }
    }


}