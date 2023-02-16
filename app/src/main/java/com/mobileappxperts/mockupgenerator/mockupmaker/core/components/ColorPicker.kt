package com.mobileappxperts.mockupgenerator.mockupmaker.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import com.mobileappxperts.mockupgenerator.mockupmaker.R
import com.mobileappxperts.mockupgenerator.mockupmaker.core.utils.ColorPicker
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.AppColor
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.AppFonts
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogButtons
import com.vanpra.composematerialdialogs.MaterialDialogScope
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@Composable
fun ColorPicker(
    colors: List<ColorPicker?>,
    selectedColor: MutableState<Color>,
    onColorSelected: (color: ColorPicker) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        FlowRow(
            mainAxisAlignment = MainAxisAlignment.Start,
            mainAxisSize = SizeMode.Wrap,
            crossAxisSpacing = 4.dp,
            mainAxisSpacing = 4.dp
        ) {
            colors.distinct().forEach { color ->
                ColorItem(
                    selected = color?.primaryColor == selectedColor.value,
                    color = color?.primaryColor,
                    onClick = {
                        if (color != null) {
                            onColorSelected(color)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ColorItem(
    selected: Boolean,
    color: Color?,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clip(CircleShape)
            .requiredSize(40.dp)
            .clickable(onClick = onClick)
    ) {
        if (color != null) {
            // Transparent background pattern
            Box(
                modifier = Modifier
                    .width(20.dp)
                    .fillMaxHeight()
                    .background(Color(0xFFBDBDBD))
            )
            // Color indicator
            val colorModifier =
//                if (/*color.luminance() < 0.4 || */color.luminance() > 0.7) {
                if (color.luminance() < 0.1 || color.luminance() > 0.9) {
                    Modifier
                        .fillMaxSize()
                        .background(color)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colors.onSurface,
                            shape = CircleShape
                        )
                } else {
                    Modifier
                        .fillMaxSize()
                        .background(color)
                }
            Box(
                modifier = colorModifier
            ) {
                if (selected) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = Icons.Default.Check.name,
                        tint = if (color.luminance() < 0.5) Color.White else Color.Black,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        } else {
            if (selected) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                        .background(
                            if (isSystemInDarkTheme()) Color(0x33FFFFFF) else Color(
                                0x33000000
                            )
                        )
                )
            }
            // Color null indicator
            Icon(
                painterResource(R.drawable.ic_color_off_24dp),
                contentDescription = Icons.Default.Clear.name,
                modifier = Modifier.align(Alignment.Center),
                tint = contentColorFor(MaterialTheme.colors.surface)
            )
        }
    }
}

@Composable
fun DialogAndShowButton(
    buttonText: String,
    buttons: @Composable MaterialDialogButtons.() -> Unit = {},
    content: @Composable MaterialDialogScope.() -> Unit
) {
    val dialogState = rememberMaterialDialogState()

    MaterialDialog(dialogState = dialogState, buttons = buttons) {
        content()
    }

    TextButton(
        onClick = { dialogState.show() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colors.primaryVariant)
    ) {
        Text(
            buttonText,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            color = MaterialTheme.colors.onPrimary
        )
    }
}

@Composable
fun MaterialDialogButtons.defaultColorDialogButtons() {
    positiveButton(
        text = "Done",
        textStyle = TextStyle(
            color = AppColor,
            fontWeight = FontWeight.Bold,
            fontFamily = AppFonts
        )
    )
    negativeButton(
        text = "Cancel",
        textStyle = TextStyle(
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            fontFamily = AppFonts
        )
    )
}