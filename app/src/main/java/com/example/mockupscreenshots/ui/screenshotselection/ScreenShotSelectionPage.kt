package com.example.mockupscreenshots.ui.screenshotselection

import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mockupscreenshots.R
import com.example.mockupscreenshots.core.ext.capture
import com.example.mockupscreenshots.core.ext.saveBitmap
import com.example.mockupscreenshots.ui.DeviceFrameView
import com.example.mockupscreenshots.ui.DeviceFrameViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScreenShotSelectionPage(frameId: String, onNext: () -> Unit) {
    val deviceFrameViewModel = hiltViewModel<DeviceFrameViewModel>()
    val frames = deviceFrameViewModel.state.frameItems
    val context = LocalContext.current
    if (frames.isNotEmpty()) {
        val deviceFrameView: MutableState<DeviceFrameView> = remember {
            mutableStateOf(DeviceFrameView(context = context, bitmap = null, frame = mutableStateOf(frames[0])))
        }
        val frame = frames.find { it.frameId == frameId }
        val bitmap: MutableState<Bitmap> = remember {
            mutableStateOf(Bitmap.createBitmap(100, 100, Bitmap.Config.ALPHA_8))
        }
        val galleryLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            }
        frame?.let {
            Box(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.dog_paws_blue),
                        contentDescription = null
                    )
                    AndroidView(modifier = Modifier, factory = {
                        DeviceFrameView(
                            context = it,
                            frame = mutableStateOf(frame),
                            bitmap = bitmap
                        ).apply {
                            post {
                                deviceFrameView.value = this
                            }
                        }
                    })

                }
                Card(
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.BottomEnd),
                    shape = CircleShape,
                    elevation = 5.dp,
                    onClick = {
                        galleryLauncher.launch("image/*")
                    }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(54.dp)
                            .padding(10.dp),
                        painter = painterResource(id = R.drawable.insert_image),
                        contentDescription = null
                    )
                }
                Card(
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.TopEnd),
                    shape = CircleShape,
                    elevation = 5.dp,
                    onClick = {
                        context.saveBitmap(deviceFrameView.value.capture())
                        onNext()
                    }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(40.dp)
                            .padding(6.dp)
                            .rotate(180f),
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = null
                    )
                }
            }
        }
    }
}
