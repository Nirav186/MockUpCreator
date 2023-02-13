package com.example.mockupscreenshots.ui

import androidx.compose.foundation.Image
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

@Composable
fun FullMockUps() {
    val context = LocalContext.current
    val deviceFrameViewModel = hiltViewModel<DeviceFrameViewModel>()
    val frames = deviceFrameViewModel.state.frameItems
    if (frames.isNotEmpty()) {
        var currentFrame by remember {
            mutableStateOf(56)
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