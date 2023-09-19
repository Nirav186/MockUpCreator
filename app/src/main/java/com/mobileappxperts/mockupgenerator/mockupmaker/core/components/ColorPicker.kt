package com.mobileappxperts.mockupgenerator.mockupmaker.core.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mobileappxperts.mockupgenerator.mockupmaker.R
import com.mobileappxperts.mockupgenerator.mockupmaker.core.BackgroundState
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.BackgroundModel

@Composable
fun ColorPicker(
    colors: List<Color?>,
    backgroundState: BackgroundState,
    onColorSelected: (Color?) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier, content = {
            items(colors) { color ->
                var isSelected = false
                if (backgroundState is BackgroundState.BackgroundColor) {
                    isSelected = backgroundState.color == color
                }
                ColorItem(
                    selected = isSelected,
                    color = color,
                    onClick = {
                        onColorSelected(color)
                    }
                )
            }
        },
        contentPadding = PaddingValues(horizontal = 12.dp)
    )
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
    bgs: List<BackgroundModel?>,
    onBgSelected: (BackgroundModel?) -> Unit,
    modifier: Modifier = Modifier,
    backgroundState: BackgroundState
) {
    LazyRow(
        modifier = modifier, content = {
            items(bgs) { bg ->
                var selected = false
                if (backgroundState is BackgroundState.Background) {
                    selected = backgroundState.backgroundModel == bg
                }
                BgItem(
                    selected = selected,
                    bg = bg,
                    onClick = {
                        onBgSelected(bg)
                    })
            }
        },
        contentPadding = PaddingValues(horizontal = 12.dp)
    )
}

@Composable
fun BgItem(
    selected: Boolean,
    bg: BackgroundModel?,
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
                AsyncImage(
                    model = "file:///android_asset/${bg.name}",
                    contentDescription = null
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

@Composable
fun GradientPicker(
    gradients: List<String>,
    onGradientSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    backgroundState: BackgroundState
) {
    LazyRow(
        modifier = modifier, content = {
            items(gradients) { gradient ->
                var selected = false
                if (backgroundState is BackgroundState.BackgroundGradient) {
                    selected = backgroundState.gradient == gradient
                }
                GradientItem(
                    selected = selected,
                    gradientName = gradient,
                    onClick = {
                        onGradientSelected(gradient)
                    })
            }
        },
        contentPadding = PaddingValues(horizontal = 12.dp)
    )
}

@Composable
fun GradientItem(
    selected: Boolean,
    gradientName: String?,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clip(CircleShape)
            .requiredSize(40.dp)
            .clickable(onClick = onClick)
    ) {
        if (gradientName != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colors.onSurface,
                        shape = CircleShape
                    )
            ) {
                val drawableId = remember(gradientName) {
                    context.resources.getIdentifier(
                        gradientName,
                        "drawable",
                        context.packageName
                    )
                }
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(id = drawableId),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
                if (selected) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = Icons.Default.Check.name,
                        tint = /*if (color.luminance() < 0.5) Color.White else*/ Color.Black,
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