package com.example.mockupscreenshots.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mockupscreenshots.ui.theme.AppColor
import com.example.mockupscreenshots.ui.theme.AppFonts

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppButton(
    modifier: Modifier,
    buttonText: String,
    onClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .height(50.dp),
            elevation = 0.dp,
            onClick = {
                focusManager.clearFocus()
                onClick()
            },
            backgroundColor = AppColor
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = AppColor),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = buttonText, fontSize = 16.sp,
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = AppFonts
                    )
                )
            }
        }
    }
}

@Composable
fun ButtonWithBg(
    modifier: Modifier,
    imageVector: ImageVector,
    color: Color
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color.copy(alpha = 0.15f)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.padding(8.dp),
            imageVector = imageVector,
            contentDescription = null,
            tint = color
        )
    }
}