package com.mobileappxperts.mockupgenerator.mockupmaker.ui.view

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.AppColor

class ScreenshotView @JvmOverloads constructor(
    val modifier: Modifier,
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    val title: MutableState<String>,
    val subTitle: MutableState<String>,
    val deviceFrameView: MutableState<DeviceFrameView>,
    val bitmap: MutableState<Bitmap>,
    val selectedBgColor: MutableState<Color?>,
    val selectedBg: MutableState<Int?>,
    val textColor: MutableState<Color>,
    val isLoading: MutableState<Boolean>
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
                        } else selectedBgColor.value ?: Color.White
                    ),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .padding(horizontal = 30.dp)
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
                        .padding(horizontal = 30.dp)
                        .fillMaxWidth(),
                    text = subTitle.value,
                    style = TextStyle(
                        color = textColor.value,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                if (isLoading.value) {
                    Box(modifier = modifier.fillMaxHeight()) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = AppColor
                        )
                    }
                } else {
                    if (deviceFrameView.value.width > 0 && deviceFrameView.value.height > 0) {
                        SubcomposeAsyncImage(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 20.dp, bottom = 40.dp),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(bitmap.value)
                                .build(),
                            contentDescription = null,
                            loading = {
                                Box(modifier = modifier.fillMaxHeight()) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.align(Alignment.Center),
                                        color = AppColor
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }

}