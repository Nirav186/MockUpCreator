package com.mobileappxperts.mockupgenerator.mockupmaker.ui.addscreenshot

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Looper
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mobileappxperts.mockupgenerator.mockupmaker.R
import com.mobileappxperts.mockupgenerator.mockupmaker.core.components.AppButton
import com.mobileappxperts.mockupgenerator.mockupmaker.core.components.CustomTextField
import com.mobileappxperts.mockupgenerator.mockupmaker.core.components.defaultColorDialogButtons
import com.mobileappxperts.mockupgenerator.mockupmaker.core.ext.capture
import com.mobileappxperts.mockupgenerator.mockupmaker.core.ext.saveHomeScreenshot
import com.mobileappxperts.mockupgenerator.mockupmaker.core.ext.saveScreenshot
import com.mobileappxperts.mockupgenerator.mockupmaker.core.utils.AdManager
import com.mobileappxperts.mockupgenerator.mockupmaker.core.utils.Constants
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.DeviceFrameItem
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.HomeFrame
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.Project
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.deviceframe.DeviceFrameViewModel
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.project.ProjectViewModel
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.AppColor
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.BgColor
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.SecondaryColor
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.SecondaryColorBG
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.view.DeviceFrameView
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.view.ScreenshotView
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.color.colorChooser
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.vanpra.composematerialdialogs.title
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddScreenshot(
    navHostController: NavHostController,
    homeFrame: HomeFrame? = null,
    project: Project? = null
) {
    val deviceFrameViewModel = hiltViewModel<DeviceFrameViewModel>()
    val projectViewModel = hiltViewModel<ProjectViewModel>()
    val frames = deviceFrameViewModel.state.frameItems
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    if (frames.isNotEmpty()) {
        val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
            confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded })

        val title = remember { mutableStateOf("App name") }
        val subTitle = remember { mutableStateOf("Add app description here") }

        val selectedBgColor = remember { mutableStateOf(Color(0xFF606c38)) }
        val selectedTextColor = remember { mutableStateOf(Color.White) }
        val selectedBg: MutableState<Int?> = remember { mutableStateOf(null) }

        var selectedBottomSheetOption by remember {
            mutableStateOf(BottomPanelSelectedOption.DEVICE_FRAME_SELECTION)
        }
        val selectedFrame = remember(frames) { mutableStateOf(frames[0]) }

        ModalBottomSheetLayout(
            modifier = Modifier.fillMaxSize(),
            sheetState = sheetState,
            sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            sheetContent = {
                when (selectedBottomSheetOption) {
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

                val dialogState = rememberMaterialDialogState()

                LaunchedEffect(key1 = true, block = {
                    homeFrame?.let {
                        frames.find { it.frameId == homeFrame.frameId }?.let {
                            selectedFrame.value = it
                            selectedBgColor.value = Color(homeFrame.backgroundColor.toULong())
                            selectedTextColor.value =
                                if (homeFrame.textColor == 0) Color.Black else Color.White
                        }
                    }
                })

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BgColor)
                ) {
                    MaterialDialog(
                        dialogState = dialogState,
                        buttons = { defaultColorDialogButtons() }
                    ) {
                        title("Select a Color")
                        colorChooser(
                            colors = Constants.bgColors.map { it.primaryColor },
                            waitForPositiveButton = true
                        ) { color ->
                            selectedBgColor.value = color
                            selectedTextColor.value =
                                (Constants.bgColors.find { it.primaryColor == color }
                                    ?: Constants.bgColors[1]).secondaryColor
                        }
                    }

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
                        BottomPanel(modifier = Modifier.padding(10.dp),
                            onPaletteClick = {
                                coroutineScope.launch {
                                    dialogState.show()
                                }
                            },
                            onPhoneClick = {
                                coroutineScope.launch {
                                    if (sheetState.isVisible) {
                                        sheetState.hide()
                                    } else {
                                        selectedBottomSheetOption =
                                            BottomPanelSelectedOption.DEVICE_FRAME_SELECTION
                                        sheetState.show()
                                    }
                                }
                            },
                            onSaveClick = {
                                AdManager().showInterstitial(context as ComponentActivity)
                                if (homeFrame == null) {
                                    val filePath =
                                        screenshotView.value.capture().saveScreenshot(context)
                                    project?.let {
                                        project.screenshots.add(filePath)
                                        projectViewModel.addProject(project)
                                    }
                                    navHostController.previousBackStackEntry?.savedStateHandle?.set(
                                        "filePath",
                                        filePath
                                    )
                                } else {
                                    screenshotView.value.capture().saveHomeScreenshot(context)
                                }
                                navHostController.navigateUp()
                            },
                            onAddImageClick = {
                                galleryLauncher.launch("image/*")
                            },
                            onAddText = {
                                coroutineScope.launch {
                                    if (sheetState.isVisible) {
                                        sheetState.hide()
                                    } else {
                                        selectedBottomSheetOption =
                                            BottomPanelSelectedOption.TEXT_EDIT
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
            model = frame.frameUrl,
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
                .size(36.dp),
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
                .size(36.dp),
            painter = painterResource(id = R.drawable.ic_mockup),
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
                .size(40.dp),
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TextBottomSheet(
    sheetState: ModalBottomSheetState,
    title: String,
    subTitle: String,
    onDone: (titleString: String, subTitle: String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var titleText by remember { mutableStateOf(title) }
    var subTitleText by remember { mutableStateOf(subTitle) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(horizontal = 10.dp, vertical = 26.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            text = "Add Text",
            color = Color.Black,
            style = TextStyle(
                fontSize = 22.sp, color = Color.Black, fontWeight = FontWeight.Bold
            )
        )
        CustomTextField(value = titleText, onValueChange = {
            titleText = it
        }, placeholderText = "Project Name")
        Spacer(modifier = Modifier.height(14.dp))
        CustomTextField(value = subTitleText, onValueChange = {
            subTitleText = it
        }, placeholderText = "Sub Title")
        Spacer(modifier = Modifier.height(14.dp))
        AppButton(
            modifier = Modifier
                .padding(end = 10.dp)
                .width(150.dp)
                .align(Alignment.End), buttonText = "Done"
        ) {
            coroutineScope.launch {
                sheetState.hide()
            }
            onDone(titleText, subTitleText)
        }
    }
}

@Composable
fun DeviceFrameBottomSheet(
    frames: List<DeviceFrameItem>,
    onFrameSelect: (DeviceFrameItem) -> Unit
) {
    var selectedTabPos by remember {
        mutableStateOf(0)
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Choose Frame",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            )
            Row(
                modifier = Modifier
                    .width(140.dp)
                    .padding(end = 10.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = AppColor),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(1.dp)
                        .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
                        .background(if (selectedTabPos == 0) AppColor else Color.White)
                        .clickable(onClick = {
                            selectedTabPos = 0
                        })
                        .padding(6.dp),
                    text = "Android",
                    style = TextStyle(
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(1.dp)
                        .clip(RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
                        .background(if (selectedTabPos == 0) Color.White else AppColor)
                        .clickable(onClick = {
                            selectedTabPos = 1
                        })
                        .padding(6.dp),
                    text = "iOS",
                    style = TextStyle(
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
        if (selectedTabPos == 0) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                contentPadding = PaddingValues(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(frames.filter { it.deviceType.equals("Android", ignoreCase = true) }) {
                    SmallFrameImg(it, onClick = {
                        onFrameSelect(it)
                    })
                }
            }
        } else {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                contentPadding = PaddingValues(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(frames.filter { it.deviceType.equals("iOS", ignoreCase = true) }) {
                    SmallFrameImg(it, onClick = {
                        onFrameSelect(it)
                    })
                }
            }
        }
    }
}

enum class BottomPanelSelectedOption {
    DEVICE_FRAME_SELECTION, TEXT_EDIT
}