package com.example.mockupscreenshots.ui.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mockupscreenshots.R
import com.example.mockupscreenshots.core.components.DropDownMenu
import com.example.mockupscreenshots.data.model.Project
import com.example.mockupscreenshots.ui.theme.AppFonts
import com.example.mockupscreenshots.ui.theme.BgColor

@Composable
fun CreateProject() {

    val projectViewModel: ProjectViewModel = hiltViewModel()

    var projectName by remember { mutableStateOf("") }
    var projectDes by remember { mutableStateOf("") }
    var device by remember { mutableStateOf("Device") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgColor),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(vertical = 10.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Create Project",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = AppFonts
                )
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFf3f3f3)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.ic_unselect_home),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .border(
                    width = 2.dp,
                    color = Color(0xFFD2E0ED),
                    shape = RoundedCornerShape(14.dp)
                )
        ) {
            TextField(value = projectName,
                onValueChange = {
                    projectName = it
                }, placeholder = {
                    Text(
                        "Project Name", color = Color.Gray
                    )
                }, colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Gray,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 20.dp)
                .border(
                    width = 2.dp, color = Color(0xFFD2E0ED),
                    shape = RoundedCornerShape(14.dp)
                )
        ) {
            TextField(value = projectDes, onValueChange = {
                projectDes = it
            }, placeholder = {
                Text(
                    "Project Description", color = Color.Gray
                )
            }, colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Gray,
                disabledTextColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
            )
        }
        DropDownMenu(
            modifier = Modifier,
            items = listOf("Android","Ios"),
            selectedItem = device,
            onItemSelect = {
                device = it
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .border(
                    width = 2.dp,
                    color = Color(0xFFD2E0ED),
                    shape = RoundedCornerShape(14.dp)
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Screenshot Style",
                color = Color.Gray,
                fontSize = 16.sp
            )
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_down),
                contentDescription = null,
                tint = Color(0xFF93A2B2)
            )
        }
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(180.dp)
                .dashedBorder(1.dp, Color(0xFFB1BCC7), 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = null,
                    tint = Color(0xFF93A2B2)
                )
                Text(
                    text = "Add Your Screenshot",
                    color = Color(0xFF93A2B2)
                )
            }
        }
        Image(
            modifier = Modifier
                .width(300.dp)
                .height(100.dp)
                .clickable(onClick = {
                    projectViewModel.addProject(
                        Project(
                            name = projectName,
                            description = projectDes,
                            device = device
                        )
                    )
                }),
            painter = painterResource(id = R.drawable.ic_btn_save),
            contentDescription = null
        )
    }
}

fun Modifier.dashedBorder(strokeWidth: Dp, color: Color, cornerRadiusDp: Dp) = composed(factory = {
    val density = LocalDensity.current
    val strokeWidthPx = density.run { strokeWidth.toPx() }
    val cornerRadiusPx = density.run { cornerRadiusDp.toPx() }

    this.then(Modifier.drawWithCache {
        onDrawBehind {
            val stroke = Stroke(
                width = strokeWidthPx,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
            )

            drawRoundRect(
                color = color, style = stroke, cornerRadius = CornerRadius(cornerRadiusPx)
            )
        }
    })
})