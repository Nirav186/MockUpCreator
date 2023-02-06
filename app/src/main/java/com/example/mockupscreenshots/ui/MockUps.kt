package com.example.mockupscreenshots.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mockupscreenshots.R
import com.example.mockupscreenshots.core.getImageFromAsset
import com.example.mockupscreenshots.data.model.DeviceFrameItem

@Composable
fun FullMockUps() {
    val context = LocalContext.current
    val deviceFrameViewModel = hiltViewModel<DeviceFrameViewModel>()
    val frames = deviceFrameViewModel.state.frameItems
    if (frames.isNotEmpty()) {
        var currentFrame by remember {
            mutableStateOf(39)
        }
        val frame by remember(currentFrame) {
            mutableStateOf(frames[currentFrame])
        }
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .width((frame.width).dp)
                        .height((frame.height).dp)
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(frame.getPadding()),
                        painter = painterResource(id = R.drawable.sss),
                        contentDescription = "ScreenShot",
                        contentScale = ContentScale.FillBounds
                    )
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(
                    modifier = Modifier.clickable(onClick = {
                        if (currentFrame != 0) currentFrame--
                    }), imageVector = Icons.Default.ArrowBackIosNew, contentDescription = null
                )
                Icon(
                    modifier = Modifier
                        .rotate(180f)
                        .clickable(onClick = {
                            if (currentFrame != (frames.size - 1)) currentFrame++
                        }), imageVector = Icons.Default.ArrowBackIosNew, contentDescription = null
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = frame.deviceName,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun HalfMockUp() {
    val context = LocalContext.current
    val deviceFrameViewModel = hiltViewModel<DeviceFrameViewModel>()
    val frames = deviceFrameViewModel.state.frameItems
    if (frames.isNotEmpty()) {
        var currentFrame by remember {
            mutableStateOf(0)
        }
        val frame by remember(currentFrame) {
            mutableStateOf(frames[currentFrame])
        }
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                Column {
                    Spacer(modifier = Modifier.height(240.dp))
                    Box(
                        modifier = Modifier
                            .width((frame.width).dp)
                            .height((frame.height).dp)
                    ) {
                        Image(
                            modifier = Modifier
                                .width((frame.width).dp)
                                .height((frame.height).dp)
                                .padding(frame.getPadding()),
                            painter = painterResource(id = R.drawable.s1),
                            contentDescription = "ScreenShot",
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.TopCenter
                        )
                        context.getImageFromAsset(frame.frameId)?.let {
                            Image(
                                modifier = Modifier
                                    .width((frame.width).dp)
                                    .height((frame.height).dp),
                                bitmap = it,
                                contentDescription = "Frame",
                                contentScale = ContentScale.Crop,
                                alignment = Alignment.TopCenter
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(
                    modifier = Modifier.clickable(onClick = {
                        if (currentFrame != 0) currentFrame--
                    }), imageVector = Icons.Default.ArrowBackIosNew, contentDescription = null
                )
                Icon(
                    modifier = Modifier
                        .rotate(180f)
                        .clickable(onClick = {
                            if (currentFrame != (frames.size - 1)) currentFrame++
                        }), imageVector = Icons.Default.ArrowBackIosNew, contentDescription = null
                )
            }
        }
    }
}

@Composable
fun InclineMockUp() {
    val context = LocalContext.current
    val deviceFrameViewModel = hiltViewModel<DeviceFrameViewModel>()
    val frames = deviceFrameViewModel.state.frameItems
    if (frames.isNotEmpty()) {
        var currentFrame by remember {
            mutableStateOf(0)
        }
        val frame by remember(currentFrame) {
            mutableStateOf(frames[currentFrame])
        }
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .width((frame.width).dp)
                        .height((frame.height).dp)
                        .rotate(25f)
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(frame.getPadding()),
                        painter = painterResource(id = R.drawable.s1),
                        contentDescription = "ScreenShot",
                        contentScale = ContentScale.FillBounds
                    )
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(
                    modifier = Modifier.clickable(onClick = {
                        if (currentFrame != 0) currentFrame--
                    }), imageVector = Icons.Default.ArrowBackIosNew, contentDescription = null
                )
                Icon(
                    modifier = Modifier
                        .rotate(180f)
                        .clickable(onClick = {
                            if (currentFrame != (frames.size - 1)) currentFrame++
                        }), imageVector = Icons.Default.ArrowBackIosNew, contentDescription = null
                )
            }
        }
    }
}

@Composable
fun MockUp1() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width((855 * 1.8 * 0.1876).dp)
                .height((1658 * 1.8 * 0.1876).dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 14.dp, end = 14.dp, top = 63.dp, bottom = 65.dp),
                painter = painterResource(id = R.drawable.sss),
                contentDescription = "ScreenShot",
                contentScale = ContentScale.FillBounds
            )
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.frame_basic),
                contentDescription = "Frame",
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Composable
fun MockUp2() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width((855 * 1.8 * 0.1876).dp)
                .height((1658 * 1.8 * 0.1876).dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 14.dp, end = 14.dp, top = 63.dp, bottom = 65.dp),
                painter = painterResource(id = R.drawable.sss),
                contentDescription = "ScreenShot",
                contentScale = ContentScale.FillBounds
            )
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.frame_basic),
                contentDescription = "Frame",
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Composable
fun DeviceFrame(modifier: Modifier, frame: DeviceFrameItem) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.dog_paws_blue),
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .width((frame.width).dp)
                .height((frame.height).dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(frame.getPadding()),
                painter = painterResource(id = R.drawable.sss),
                contentDescription = "ScreenShot",
                contentScale = ContentScale.FillBounds
            )
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

@Composable
fun ScreenShotScreen() {
    Box(modifier = Modifier.fillMaxWidth()) {

    }
}