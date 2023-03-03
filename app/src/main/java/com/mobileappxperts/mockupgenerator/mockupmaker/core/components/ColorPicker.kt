package com.mobileappxperts.mockupgenerator.mockupmaker.core.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
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
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import com.mobileappxperts.mockupgenerator.mockupmaker.R

@Composable
fun ColorPicker(
    colors: List<Color?>,
    selectedColor: MutableState<Color?>,
    onColorSelected: (Color?) -> Unit,
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
                    selected = color == selectedColor.value,
                    color = color,
                    onClick = {
                        onColorSelected(color)
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
            Box(
                modifier = Modifier
                    .width(20.dp)
                    .fillMaxHeight()
                    .background(Color(0xFFBDBDBD))
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colors.onSurface,
                        shape = CircleShape
                    )
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
                            if (isSystemInDarkTheme())
                                Color(0x33FFFFFF)
                            else Color(0x33000000)
                        )
                )
            }
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
fun BgPicker(
    bgs: List<Int?>,
    selectedBg: MutableState<Int?>,
    onBgSelected: (Int?) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        FlowRow(
            mainAxisAlignment = MainAxisAlignment.Start,
            mainAxisSize = SizeMode.Wrap,
            crossAxisSpacing = 4.dp,
            mainAxisSpacing = 4.dp
        ) {
            bgs.distinct().forEach { bg ->
                BgItem(selected = selectedBg.value == bg, bg = bg, onClick = {
                    onBgSelected(bg)
                })
            }
        }
    }
}

@Composable
fun BgItem(
    selected: Boolean,
    bg: Int?,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clip(CircleShape)
            .requiredSize(40.dp)
            .clickable(onClick = onClick)
    ) {
        if (bg != null) {
            Box(
                modifier = Modifier
                    .width(20.dp)
                    .fillMaxHeight()
                    .background(Color(0xFFBDBDBD))
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colors.onSurface,
                        shape = CircleShape
                    )
            ) {
                Image(
                    painter = painterResource(id = bg), contentDescription = null
                )
                if (selected) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = Icons.Default.Check.name,
                        tint = Color.Black,
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
                            if (isSystemInDarkTheme())
                                Color(0x33FFFFFF)
                            else Color(0x33000000)
                        )
                )
            }
            Icon(
                painterResource(R.drawable.ic_color_off_24dp),
                contentDescription = Icons.Default.Clear.name,
                modifier = Modifier.align(Alignment.Center),
                tint = contentColorFor(MaterialTheme.colors.surface)
            )
        }
    }
}