package com.example.mockupscreenshots.ui.addscreenshot

import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.mockupscreenshots.R
import com.example.mockupscreenshots.core.ext.capture
import com.example.mockupscreenshots.data.model.DeviceFrameItem
import com.example.mockupscreenshots.ui.DeviceFrameView
import com.example.mockupscreenshots.ui.DeviceFrameViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddScreenshot() {
    val deviceFrameViewModel = hiltViewModel<DeviceFrameViewModel>()
    val frames = deviceFrameViewModel.state.frameItems
    val context = LocalContext.current

    if (frames.isNotEmpty()) {
        val selectedFrame = remember(frames) {
            mutableStateOf(frames[0])
        }
        val bitmap: MutableState<Bitmap> = remember {
            mutableStateOf(Bitmap.createBitmap(100, 100, Bitmap.Config.ALPHA_8))
        }
        val deviceFrameView: MutableState<DeviceFrameView> = remember {
            mutableStateOf(DeviceFrameView(context = context, bitmap = null, frame = selectedFrame))
        }
        var imageBitmap by remember(bitmap) {
            mutableStateOf(
                bitmap.value.asImageBitmap()
            )
        }

        imageBitmap = if (deviceFrameView.value.width > 0 && deviceFrameView.value.height > 0) {
            deviceFrameView.value.capture().asImageBitmap()
        } else {
            bitmap.value.asImageBitmap()
        }

        val galleryLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            }

        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(modifier = Modifier.alpha(0f), factory = {
                DeviceFrameView(
                    context = it,
                    frame = selectedFrame,
                    bitmap = bitmap
                ).apply {
                    post {
                        deviceFrameView.value = this
                    }
                }
            })
            Column {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.dog_paws_blue),
                        contentDescription = null
                    )
                    if (deviceFrameView.value.width > 0 && deviceFrameView.value.height > 0) {
                        Image(
                            bitmap = imageBitmap,
                            contentDescription = null
                        )
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
                }
                LazyRow(contentPadding = PaddingValues(horizontal = 5.dp)) {
                    items(frames) { frame ->
                        SmallFrameImg(frame, onClick = {
                            selectedFrame.value = frame
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun SmallFrameImg(frame: DeviceFrameItem, onClick: () -> Unit) {
    AsyncImage(
        modifier = Modifier
            .height(100.dp)
            .padding(horizontal = 5.dp)
            .clickable(onClick = onClick),
        model = "file:///android_asset/${frame.frameId}",
        contentDescription = null
    )
}