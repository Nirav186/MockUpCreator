package com.example.mockupscreenshots.ui.addscreenshot

import android.Manifest
import android.app.Activity
import android.graphics.Bitmap
import android.os.Looper
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.mockupscreenshots.R
import com.example.mockupscreenshots.core.components.ColorPicker
import com.example.mockupscreenshots.core.ext.capture
import com.example.mockupscreenshots.core.ext.hasPermissions
import com.example.mockupscreenshots.core.ext.saveScreenshot
import com.example.mockupscreenshots.core.utils.ColorPicker
import com.example.mockupscreenshots.core.utils.Constants
import com.example.mockupscreenshots.data.model.DeviceFrameItem
import com.example.mockupscreenshots.ui.DeviceFrameView
import com.example.mockupscreenshots.ui.DeviceFrameViewModel
import com.example.mockupscreenshots.ui.ScreenshotView
import com.example.mockupscreenshots.ui.theme.AppColor
import com.example.mockupscreenshots.ui.theme.BgColor
import com.example.mockupscreenshots.ui.theme.SecondaryColor
import com.example.mockupscreenshots.ui.theme.SecondaryColorBG
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddScreenshot(navHostController: NavHostController) {
    val deviceFrameViewModel = hiltViewModel<DeviceFrameViewModel>()
    val frames = deviceFrameViewModel.state.frameItems
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    if (frames.isNotEmpty()) {
        val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
            confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded })

        val title = remember { mutableStateOf("Mockup Android App") }
        val subTitle =
            remember { mutableStateOf("Edit the src configuration to match your needs and run it edit the src configuration to match your needs and run it") }

        val selectedBgColor = remember { mutableStateOf(Color(0xFFAE1B2B)) }
        val selectedTextColor = remember { mutableStateOf(Color.White) }
        val selectedBg: MutableState<Int?> = remember { mutableStateOf(null) }

        var selectedBottomSheetOption by remember {
            mutableStateOf(BottomPanelSelectedOption.BG_COLOR_SELECTION)
        }
        val selectedFrame = remember(frames) { mutableStateOf(frames[0]) }

        ModalBottomSheetLayout(
            modifier = Modifier.fillMaxSize(),
            sheetState = sheetState,
            sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            sheetContent = {
                when (selectedBottomSheetOption) {
                    BottomPanelSelectedOption.BG_COLOR_SELECTION -> {
                        ColorBottomSheet(sheetState = sheetState, onColorSelected = {
                            selectedBg.value = null
                            selectedBgColor.value = it.primaryColor
                            selectedTextColor.value = it.secondaryColor
                        }, onBgSelect = {
                            selectedTextColor.value = Color.White
                            selectedBg.value = it
                        })
                    }
                    BottomPanelSelectedOption.TEXT_EDIT -> {
                        TextBottomSheet(
                            sheetState, title.value, subTitle.value
                        ) { titleString: String, subTitleString: String ->
                            title.value = titleString
                            subTitle.value = subTitleString
                        }
                    }
                    BottomPanelSelectedOption.DEVICE_FRAME_SELECTION -> {
                        DeviceFrameBottomSheet(frames = frames) {
                            selectedFrame.value = it
                            coroutineScope.launch {
                                sheetState.hide()
                            }
                        }
                    }
                }
            },
        ) {
            Scaffold {
                val bitmap: MutableState<Bitmap> = remember {
                    mutableStateOf(Bitmap.createBitmap(100, 100, Bitmap.Config.ALPHA_8))
                }
                val deviceFrameView: MutableState<DeviceFrameView> = remember {
                    mutableStateOf(
                        DeviceFrameView(
                            context = context, bitmap = null, frame = selectedFrame
                        )
                    )
                }

                val imageBitmap = remember(bitmap) { mutableStateOf(bitmap.value) }
                imageBitmap.value =
                    if (deviceFrameView.value.width > 0 && deviceFrameView.value.height > 0) {
                        deviceFrameView.value.capture()
                    } else {
                        bitmap.value
                    }

                val screenshotView: MutableState<ScreenshotView> = remember {
                    mutableStateOf(
                        ScreenshotView(
                            modifier = Modifier,
                            context = context,
                            title = title,
                            subTitle = subTitle,
                            deviceFrameView = deviceFrameView,
                            bitmap = bitmap,
                            selectedBgColor = selectedBgColor,
                            selectedBg = selectedBg,
                            textColor = selectedTextColor
                        )
                    )
                }

                val galleryLauncher =
                    rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                        uri?.let {
                            bitmap.value =
                                MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                            android.os.Handler(Looper.getMainLooper()).postDelayed({
                                imageBitmap.value = deviceFrameView.value.capture()
                            }, 100)
                        }
                    }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BgColor)
                ) {
                    key(selectedFrame.value) {
                        AndroidView(modifier = Modifier.alpha(0f), factory = {
                            DeviceFrameView(
                                context = it, frame = selectedFrame, bitmap = bitmap
                            ).apply {
                                post {
                                    deviceFrameView.value = this
                                }
                            }
                        })
                    }
                    Column(modifier = Modifier.fillMaxSize()) {
                        AndroidView(modifier = Modifier
                            .weight(1f)
                            .alpha(1f), factory = {
                            ScreenshotView(
                                modifier = Modifier.weight(1f),
                                context = it,
                                title = title,
                                subTitle = subTitle,
                                deviceFrameView = deviceFrameView,
                                bitmap = imageBitmap,
                                selectedBgColor = selectedBgColor,
                                selectedBg = selectedBg,
                                textColor = selectedTextColor
                            ).apply {
                                post {
                                    screenshotView.value = this
                                }
                            }
                        })
                        BottomPanel(modifier = Modifier.padding(10.dp), onPaletteClick = {
                            coroutineScope.launch {
                                if (sheetState.isVisible) {
                                    sheetState.hide()
                                } else {
                                    selectedBottomSheetOption =
                                        BottomPanelSelectedOption.BG_COLOR_SELECTION
                                    sheetState.show()
                                }
                            }
                        }, onPhoneClick = {
                            coroutineScope.launch {
                                if (sheetState.isVisible) {
                                    sheetState.hide()
                                } else {
                                    selectedBottomSheetOption =
                                        BottomPanelSelectedOption.DEVICE_FRAME_SELECTION
                                    sheetState.show()
                                }
                            }
                        }, onSaveClick = {
                            if (context.hasPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                val filePath = screenshotView.value.capture().saveScreenshot(context)
                                navHostController.previousBackStackEntry?.savedStateHandle?.set(
                                    "filePath",
                                    filePath
                                )
                                navHostController.navigateUp()
                            } else {
                                ActivityCompat.requestPermissions(
                                    context as Activity,
                                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                                    1
                                )
                            }
                        }, onAddImageClick = {
                            galleryLauncher.launch("image/*")
                        }, onAddText = {
                            coroutineScope.launch {
                                if (sheetState.isVisible) {
                                    sheetState.hide()
                                } else {
                                    selectedBottomSheetOption = BottomPanelSelectedOption.TEXT_EDIT
                                    sheetState.show()
                                }
                            }
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun SmallFrameImg(frame: DeviceFrameItem, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .clickable(onClick = onClick)
            .background(SecondaryColorBG.copy(alpha = 0.35f))
            .padding(10.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .height(100.dp)
                .padding(horizontal = 5.dp),
            model = "file:///android_asset/${frame.frameId}",
            contentDescription = null
        )
    }
}

@Composable
fun BottomPanel(
    modifier: Modifier,
    onPaletteClick: () -> Unit,
    onPhoneClick: () -> Unit,
    onSaveClick: () -> Unit,
    onAddImageClick: () -> Unit,
    onAddText: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color.White), verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .clickable(onClick = onPaletteClick)
                .padding(10.dp)
                .size(40.dp),
            painter = painterResource(id = R.drawable.pallete),
            contentDescription = null,
            tint = SecondaryColor
        )
        Divider(
            color = SecondaryColor, modifier = Modifier
                .height(50.dp)
                .width(1.dp)
                .alpha(0.4f)
        )
        Icon(
            modifier = Modifier
                .clickable(onClick = onPhoneClick)
                .padding(10.dp)
                .size(40.dp),
            painter = painterResource(id = R.drawable.ic_smartphone),
            contentDescription = null,
            tint = SecondaryColor
        )
        Divider(
            color = SecondaryColor, modifier = Modifier
                .height(50.dp)
                .width(1.dp)
                .alpha(0.4f)
        )
        Icon(
            modifier = Modifier
                .clickable(onClick = onAddImageClick)
                .padding(10.dp)
                .size(40.dp)
                .padding(4.dp),
            painter = painterResource(id = R.drawable.insert_image),
            contentDescription = null,
            tint = SecondaryColor
        )
        Divider(
            color = SecondaryColor, modifier = Modifier
                .height(50.dp)
                .width(1.dp)
                .alpha(0.4f)
        )
        Icon(
            modifier = Modifier
                .clickable(onClick = onAddText)
                .padding(10.dp)
                .size(40.dp)
                .padding(4.dp),
            painter = painterResource(id = R.drawable.add_text),
            contentDescription = null,
            tint = SecondaryColor
        )
        Divider(
            color = SecondaryColor, modifier = Modifier
                .height(50.dp)
                .width(1.dp)
                .alpha(0.4f)
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(AppColor.copy(alpha = 0.15f))
                .clickable(onClick = onSaveClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.padding(8.dp),
                imageVector = Icons.Default.Save,
                contentDescription = null,
                tint = AppColor
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun TextBottomSheet(
    sheetState: ModalBottomSheetState,
    title: String,
    subTitle: String,
    onDone: (titleString: String, subTitle: String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var _title by remember { mutableStateOf(TextFieldValue(title)) }
    var _subTitle by remember { mutableStateOf(TextFieldValue(subTitle)) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(10.dp)
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            text = "Add Text",
            color = Color.Black,
            style = TextStyle(
                fontSize = 22.sp, color = Color.Black, fontWeight = FontWeight.Bold
            )
        )

        //Title
        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            value = _title,
            onValueChange = {
                _title = it
            },
            label = { Text("Title") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = AppColor,
                unfocusedBorderColor = Color.Gray,
                cursorColor = AppColor,
                focusedLabelColor = AppColor,
                disabledLabelColor = Color.Gray
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
        )
        Spacer(modifier = Modifier.height(14.dp))

        //Sub Title
        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            value = _subTitle,
            onValueChange = {
                _subTitle = it
            },
            label = { Text("Sub Title") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = AppColor,
                unfocusedBorderColor = Color.Gray,
                cursorColor = AppColor,
                focusedLabelColor = AppColor,
                disabledLabelColor = Color.Gray
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
        )
        Spacer(modifier = Modifier.height(14.dp))
        Box(
            modifier = Modifier
                .padding(end = 10.dp)
                .clickable(onClick = {
                    coroutineScope.launch {
                        sheetState.hide()
                    }
                    onDone(_title.text, _subTitle.text)
                })
                .clip(RoundedCornerShape(30.dp))
                .background(AppColor)
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .align(Alignment.End)
        ) {
            Text(
                text = "Done", style = TextStyle(
                    color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Medium
                )
            )
        }
        Spacer(modifier = Modifier.height(14.dp))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ColorBottomSheet(
    sheetState: ModalBottomSheetState,
    onColorSelected: (color: ColorPicker) -> Unit,
    onBgSelect: (Int) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val colors = Constants.bgColors
    val selectedColor = remember { mutableStateOf(colors[0]) }
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Select background",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(12.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .requiredSize(40.dp)
                    .clickable(onClick = { onBgSelect(R.drawable.dog_paws_green) })
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dog_paws_green),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .requiredSize(40.dp)
                    .clickable(onClick = { onBgSelect(R.drawable.dog_paws_blue) })
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dog_paws_blue),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .requiredSize(40.dp)
                    .clickable(onClick = { onBgSelect(R.drawable.dog_paws_yellow) })
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dog_paws_yellow),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Select color",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        Divider(thickness = 1.dp, color = MaterialTheme.colors.onPrimary)
        ColorPicker(
            colors,
            selectedColor.value,
            onColorSelected = onColorSelected,
            modifier = Modifier.padding(12.dp)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Box(
            modifier = Modifier
                .padding(end = 10.dp)
                .clickable(onClick = {
                    coroutineScope.launch {
                        sheetState.hide()
                    }
//                    onDone(_title.text, _subTitle.text)
                })
                .clip(RoundedCornerShape(30.dp))
                .background(AppColor)
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .align(Alignment.End)
        ) {
            Text(
                text = "Done", style = TextStyle(
                    color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Medium
                )
            )
        }
        Spacer(modifier = Modifier.height(14.dp))
    }
}

@Composable
fun DeviceFrameBottomSheet(
    frames: List<DeviceFrameItem>, onFrameSelect: (DeviceFrameItem) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(), text = "Choose Frame", style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            contentPadding = PaddingValues(horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(frames) {
                SmallFrameImg(it, onClick = {
                    onFrameSelect(it)
                })
            }
        }
    }
}

enum class BottomPanelSelectedOption {
    BG_COLOR_SELECTION, DEVICE_FRAME_SELECTION, TEXT_EDIT
}