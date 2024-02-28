package com.mobileappxperts.mockupgenerator.mockupmaker.ui.addscreenshot

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Looper
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.mobileappxperts.mockupgenerator.mockupmaker.R
import com.mobileappxperts.mockupgenerator.mockupmaker.core.BackgroundState
import com.mobileappxperts.mockupgenerator.mockupmaker.core.components.AppButton
import com.mobileappxperts.mockupgenerator.mockupmaker.core.components.BgPicker
import com.mobileappxperts.mockupgenerator.mockupmaker.core.components.ColorPicker
import com.mobileappxperts.mockupgenerator.mockupmaker.core.components.CustomTextField
import com.mobileappxperts.mockupgenerator.mockupmaker.core.components.GradientPicker
import com.mobileappxperts.mockupgenerator.mockupmaker.core.ext.capture
import com.mobileappxperts.mockupgenerator.mockupmaker.core.ext.saveHomeScreenshot
import com.mobileappxperts.mockupgenerator.mockupmaker.core.ext.saveScreenshot
import com.mobileappxperts.mockupgenerator.mockupmaker.core.utils.Constants
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.BackgroundModel
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.DeviceFrameItem
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.HomeFrame
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.Project
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.deviceframe.DeviceFrameViewModel
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.project.ProjectViewModel
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.*
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.view.DeviceFrameView
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.view.ScreenshotView
import com.nirav.commons.ads.CommonAdManager
import com.nirav.commons.ads.CommonAdManager.showInterstitialAd
import com.raedapps.alwan.ui.AlwanDialog
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddScreenshot(
    navHostController: NavHostController,
    homeFrame: HomeFrame? = null,
    project: Project? = null,
    onBackPressed: () -> Unit
) {
    val deviceFrameViewModel = hiltViewModel<DeviceFrameViewModel>()
    val projectViewModel = hiltViewModel<ProjectViewModel>()
    val frames = deviceFrameViewModel.state.frameItems
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    BackHandler { onBackPressed() }
    if (frames.isNotEmpty()) {
        val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
            confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded })

        val title = remember { mutableStateOf(context.getString(R.string.default_title_text)) }
        val subTitle = remember { mutableStateOf(context.getString(R.string.default_des_text)) }

        val selectedTextColor = remember { mutableStateOf(Color.White) }

        val backgroundState: MutableState<BackgroundState> = remember {
            mutableStateOf(Constants.getRandomBackground())
        }

        var selectedBottomSheetOption by remember {
            mutableStateOf(BottomPanelSelectedOption.DEVICE_FRAME_SELECTION)
        }
        val selectedFrame = remember(frames) { mutableStateOf(frames[0]) }
        val isPaidFrame = remember { mutableStateOf(false) }

        ModalBottomSheetLayout(
            modifier = Modifier.fillMaxSize(),
            sheetState = sheetState,
            sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            sheetContent = {
                when (selectedBottomSheetOption) {
                    BottomPanelSelectedOption.BG_COLOR_SELECTION -> {
                        ColorBottomSheet(sheetState = sheetState, onColorSelected = {
                            backgroundState.value = BackgroundState.BackgroundColor(it)
                        }, onBgSelect = {
                            backgroundState.value = BackgroundState.Background(it)
                        }, onGradientSelect = {
                            backgroundState.value = BackgroundState.BackgroundGradient(it)
                        }, backgroundState = backgroundState.value
                        )
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
                        DeviceFrameBottomSheet(frames = frames) { frame, isPaid ->
                            coroutineScope.launch {
                                sheetState.hide()
                            }
                            isPaidFrame.value = isPaid
                            selectedFrame.value = frame
                        }
                    }
                }
            },
        ) {
            Scaffold {
                val isImageSelected = remember { mutableStateOf(false) }
                val bitmap: MutableState<Bitmap> = remember {
                    mutableStateOf(
                        BitmapFactory.decodeResource(context.resources, R.drawable.ic_default_ss)
                    )
                }
                val deviceFrameView: MutableState<DeviceFrameView> = remember {
                    mutableStateOf(
                        DeviceFrameView(context = context,
                            bitmap = null,
                            frame = selectedFrame,
                            onLoading = {})
                    )
                }

                val imageBitmap = remember(bitmap) { mutableStateOf(bitmap.value) }
                imageBitmap.value =
                    if (deviceFrameView.value.width > 0 && deviceFrameView.value.height > 0) {
                        deviceFrameView.value.capture()
                    } else {
                        bitmap.value
                    }

                val isLoading = remember { mutableStateOf(false) }
                val galleryLauncher =
                    rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                        uri?.let {
                            isImageSelected.value = true
                            bitmap.value =
                                MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                            android.os.Handler(Looper.getMainLooper()).postDelayed({
                                imageBitmap.value = deviceFrameView.value.capture()
                            }, 100)
                        }
                    }
                val screenshotView: MutableState<ScreenshotView> = remember {
                    mutableStateOf(
                        ScreenshotView(modifier = Modifier,
                            context = context,
                            title = title,
                            subTitle = subTitle,
                            deviceFrameView = deviceFrameView,
                            bitmap = bitmap,
                            textColor = selectedTextColor,
                            isLoading = isLoading,
                            backgroundState = backgroundState,
                            onTextClick = {
                                coroutineScope.launch {
                                    selectedBottomSheetOption = BottomPanelSelectedOption.TEXT_EDIT
                                    sheetState.show()
                                }
                            },
                            selectedFrame = selectedFrame,
                            isPaid = isPaidFrame,
                            isImageSelected = isImageSelected,
                            onAddImageClick = {
                                galleryLauncher.launch("image/*")
                            },
                            onAdClick = {
                                CommonAdManager.showRewardAd(
                                    activity = context as ComponentActivity,
                                    onRewardEarned = {
                                        isPaidFrame.value = false
                                    }
                                )
//                                AdManager.showRewardAd(
//                                    context as ComponentActivity,
//                                    onRewardEarned = {
//                                        isPaidFrame.value = false
//                                    })
                            })
                    )
                }
                LaunchedEffect(key1 = true, block = {
                    homeFrame?.let {
                        frames.find { it.frameId == homeFrame.frameId }?.let {
                            selectedFrame.value = it
                            homeFrame.backgroundColor?.let {
                                backgroundState.value =
                                    BackgroundState.BackgroundColor(Color(homeFrame.backgroundColor.toULong()))
                            }
                            homeFrame.backgroundGradient?.let {
                                backgroundState.value =
                                    BackgroundState.BackgroundGradient(homeFrame.backgroundGradient)
                            }
                            homeFrame.background?.let {
                                backgroundState.value = BackgroundState.Background(
                                    BackgroundModel(
                                        homeFrame.background, homeFrame.textColor
                                    )
                                )
                            }
                            selectedTextColor.value =
                                if (homeFrame.textColor == 0) Color.Black else Color.White
                        }
                    }
                })

//                var isDebugDialogShow by remember {
//                    mutableStateOf(false)
//                }
//
//                var debugString by remember {
//                    mutableStateOf("")
//                }
//
//                val clipboardManager: ClipboardManager = LocalClipboardManager.current
//
//                if (isDebugDialogShow) {
//                    CustomDialog(text = debugString) {
//                        clipboardManager.setText(AnnotatedString(debugString))
//                        Toast.makeText(context, "Copied!!", Toast.LENGTH_SHORT).show()
//                        navHostController.navigateUp()
//                    }
//                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BgColor)
                ) {
                    key(selectedFrame.value) {
                        AndroidView(modifier = Modifier.alpha(0f), factory = {
                            DeviceFrameView(context = it,
                                frame = selectedFrame,
                                bitmap = bitmap,
                                onLoading = {
                                    isLoading.value = it
                                }).apply {
                                post {
                                    deviceFrameView.value = this
                                }
                            }
                        })
                    }
                    Column(modifier = Modifier.fillMaxSize()) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .alpha(1f)
                        ) {
                            AndroidView(modifier = Modifier
                                .fillMaxSize()
                                .alpha(1f), factory = {
                                ScreenshotView(
                                    modifier = Modifier.fillMaxSize(),
                                    context = it,
                                    title = title,
                                    subTitle = subTitle,
                                    deviceFrameView = deviceFrameView,
                                    bitmap = imageBitmap,
                                    textColor = selectedTextColor,
                                    isLoading = isLoading,
                                    backgroundState = backgroundState,
                                    onTextClick = {
                                        coroutineScope.launch {
                                            selectedBottomSheetOption =
                                                BottomPanelSelectedOption.TEXT_EDIT
                                            sheetState.show()
                                        }
                                    },
                                    selectedFrame = selectedFrame,
                                    isPaid = isPaidFrame,
                                    onAdClick = {
//                                        AdManager.showRewardAd(context as ComponentActivity,
//                                            onRewardEarned = {
//                                                isPaidFrame.value = false
//                                            })
                                        CommonAdManager.showRewardAd(
                                            activity = context as ComponentActivity,
                                            onRewardEarned = {
                                                isPaidFrame.value = false
                                            })
                                    },
                                    isImageSelected = isImageSelected,
                                    onAddImageClick = {
                                        galleryLauncher.launch("image/*")
                                    }
                                ).apply {
                                    post {
                                        screenshotView.value = this
                                    }
                                }
                            })
                        }
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
                            (context as Activity).showInterstitialAd()
//                            AdManager.showInterstitialAd(context as ComponentActivity)
                            if (homeFrame == null) {
                                val filePath =
                                    screenshotView.value.capture().saveScreenshot(context)
                                project?.let {
                                    project.screenshots.add(filePath)
                                    projectViewModel.addProject(project)
                                }
                                navHostController.previousBackStackEntry?.savedStateHandle?.set(
                                    "filePath", filePath
                                )
                            } else {
                                val tempString =
                                    screenshotView.value.capture().saveHomeScreenshot(context)
//                                    if (BuildConfig.DEBUG) {
//                                        isDebugDialogShow = true
//                                        val secondaryString =
//                                            if (backgroundState.value is BackgroundState.BackgroundColor) {
//                                                (backgroundState.value as BackgroundState.BackgroundColor).color?.value.toString()
//                                            } else {
//                                                backgroundState.value.toString()
//                                            }
//                                        debugString =
//                                            "$tempString\nFrame==> ${selectedFrame.value.frameId}\nBackground==>$secondaryString"
//                                    }
                            }
                            navHostController.navigateUp()
//                                if (BuildConfig.DEBUG) {
//
//                                } else {
//                                    navHostController.navigateUp()
//                                }
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
fun SmallFrameImg(frame: DeviceFrameItem, onClick: () -> Unit, isPaid: Boolean) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .clickable(onClick = onClick)
            .background(SecondaryColorBG.copy(alpha = 0.35f))
    ) {
        SubcomposeAsyncImage(modifier = Modifier
            .padding(10.dp)
            .height(100.dp)
            .padding(horizontal = 5.dp),
            model = ImageRequest.Builder(LocalContext.current).data(frame.frameUrl).build(),
            contentDescription = null,
            loading = {
                Box(modifier = Modifier.fillMaxHeight()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center), color = AppColor
                    )
                }
            })
        if (isPaid) {
            Image(
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.TopEnd),
                painter = painterResource(id = R.drawable.ic_ads),
                contentDescription = null
            )
        }
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
        Image(
            modifier = Modifier
                .clickable(onClick = onPhoneClick)
                .padding(10.dp)
                .size(30.dp),
            painter = painterResource(id = R.drawable.ic_mockup),
            contentDescription = null
        )
        Divider(
            color = SecondaryColor, modifier = Modifier
                .height(44.dp)
                .width(1.dp)
                .alpha(0.4f)
        )
        Image(
            modifier = Modifier
                .clickable(onClick = onAddImageClick)
                .padding(10.dp)
                .size(30.dp),
            painter = painterResource(id = R.drawable.insert_image),
            contentDescription = null
        )
        Divider(
            color = SecondaryColor, modifier = Modifier
                .height(44.dp)
                .width(1.dp)
                .alpha(0.4f)
        )
        Image(
            modifier = Modifier
                .clickable(onClick = onAddText)
                .padding(10.dp)
                .size(30.dp),
            painter = painterResource(id = R.drawable.add_text),
            contentDescription = null
        )
        Divider(
            color = SecondaryColor, modifier = Modifier
                .height(44.dp)
                .width(1.dp)
                .alpha(0.4f)
        )
        Image(
            modifier = Modifier
                .clickable(onClick = onPaletteClick)
                .padding(10.dp)
                .size(30.dp),
            painter = painterResource(id = R.drawable.palette),
            contentDescription = null
        )
        Divider(
            color = SecondaryColor, modifier = Modifier
                .height(44.dp)
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
                .align(Alignment.End),
            buttonText = "Done"
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
    frames: List<DeviceFrameItem>, onFrameSelect: (frame: DeviceFrameItem, isPaid: Boolean) -> Unit
) {
    val androidFrames = frames.filter { it.deviceType.equals("android", ignoreCase = true) }
    val iosFrames = frames.filter { it.deviceType.equals("ios", ignoreCase = true) }
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
                modifier = Modifier.weight(1f), text = "Choose Frame", style = TextStyle(
                    color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 22.sp
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
                        .padding(6.dp), text = "Android", style = TextStyle(
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
                        .padding(6.dp), text = "iOS", style = TextStyle(
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
                items(androidFrames.size) {
                    SmallFrameImg(
                        frame = androidFrames[it], onClick = {
                            onFrameSelect(androidFrames[it], it > 4)
                        }, isPaid = it > 4
                    )
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
                items(iosFrames.size) {
                    SmallFrameImg(
                        frame = iosFrames[it], onClick = {
                            onFrameSelect(iosFrames[it], it > 4)
                        }, isPaid = it > 4
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ColorBottomSheet(
    sheetState: ModalBottomSheetState,
    onColorSelected: (color: Color?) -> Unit,
    onBgSelect: (BackgroundModel?) -> Unit,
    onGradientSelect: (String) -> Unit,
    backgroundState: BackgroundState
) {
    val coroutineScope = rememberCoroutineScope()
    val interactionSource = remember { MutableInteractionSource() }
    val colors = Constants.bgColors
    val dialogState = rememberMaterialDialogState()
    var selectedColorFromDialog by remember {
        mutableStateOf(Color(0xFFFFFFFF))
    }
    MaterialDialog(
        dialogState = dialogState
    ) {
        AlwanDialog(
            onDismissRequest = { },
            onColorChanged = {
                selectedColorFromDialog = it
            },
            positiveButtonText = "Ok",
            onPositiveButtonClick = {
                coroutineScope.launch {
                    dialogState.hide()
                    sheetState.hide()
                }
                onColorSelected(selectedColorFromDialog)
            },
            negativeButtonText = "Cancel",
            onNegativeButtonClick = {
                dialogState.hide()
            },
        )
    }
    Column {
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            "Select background",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        BgPicker(
            bgs = Constants.bgs, backgroundState = backgroundState, onBgSelected = {
                onBgSelect(it)
            }, modifier = Modifier.padding(vertical = 6.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Select Gradient",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        GradientPicker(
            gradients = Constants.gradients,
            backgroundState = backgroundState,
            onGradientSelected = onGradientSelect,
            modifier = Modifier.padding(vertical = 6.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp),
                text = "Select color",
                style = MaterialTheme.typography.h6
            )
            Text(
                modifier = Modifier
                    .padding(start = 10.dp, end = 30.dp)
                    .clickable(interactionSource = interactionSource, indication = null, onClick = {
                        dialogState.show()
                    }), text = "more...", style = TextStyle(
                    color = AppColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = AppFonts,
                    textAlign = TextAlign.End
                )
            )
        }
        Divider(thickness = 1.dp, color = MaterialTheme.colors.onPrimary)
        ColorPicker(
            colors = colors, backgroundState = backgroundState, onColorSelected = {
                onColorSelected(it)
            }, modifier = Modifier.padding(vertical = 6.dp)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Box(
            modifier = Modifier
                .padding(end = 10.dp)
                .clickable(onClick = {
                    coroutineScope.launch {
                        sheetState.hide()
                    }
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

enum class BottomPanelSelectedOption {
    BG_COLOR_SELECTION, DEVICE_FRAME_SELECTION, TEXT_EDIT
}