package com.mobileappxperts.mockupgenerator.mockupmaker.ui.view

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
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
import com.mobileappxperts.mockupgenerator.mockupmaker.R
import com.mobileappxperts.mockupgenerator.mockupmaker.core.BackgroundState
import com.mobileappxperts.mockupgenerator.mockupmaker.core.utils.getImageFromAsset
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.DeviceFrameItem
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
    val textColor: MutableState<Color>,
    val isLoading: MutableState<Boolean>,
    val backgroundState: MutableState<BackgroundState>,
    val onTextClick: () -> Unit,
    val selectedFrame: MutableState<DeviceFrameItem>,
    val isPaid: MutableState<Boolean>,
    val onAdClick: () -> Unit
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
            when (val state = backgroundState.value) {
                is BackgroundState.Background -> {
                    context.getImageFromAsset(state.backgroundModel?.name.toString())?.let {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            bitmap = it,
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }

                is BackgroundState.BackgroundGradient -> {
                    val drawableId = remember(state.gradient) {
                        context.resources.getIdentifier(
                            state.gradient.toString(),
                            "drawable",
                            context.packageName
                        )
                    }
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = drawableId),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                }

                else -> {

                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = when (val state = backgroundState.value) {
                            is BackgroundState.Background -> {
                                Color.Transparent
                            }

                            is BackgroundState.BackgroundColor -> {
                                state.color ?: Color.White
                            }

                            else -> {
                                Color.Transparent
                            }
                        }
                    ),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .padding(horizontal = 30.dp)
                        .fillMaxWidth()
                        .clickable(onClick = onTextClick),
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
                        .fillMaxWidth()
                        .clickable(onClick = onTextClick),
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
            if (isPaid.value && isLoading.value.not()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_watermark),
                        contentDescription = null,
                        modifier = Modifier.width((selectedFrame.value.width - 60).dp)
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_remove_watermark),
                        contentDescription = null,
                        modifier = Modifier
                            .width((selectedFrame.value.width - 60).dp)
                            .clickable(onClick = onAdClick)
                    )
                }
            }
        }
    }
}