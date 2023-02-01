package com.example.mockupscreenshots.ui.project

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mockupscreenshots.R
import com.example.mockupscreenshots.core.components.AppButton
import com.example.mockupscreenshots.data.model.Project
import com.example.mockupscreenshots.ui.theme.AppFonts
import com.example.mockupscreenshots.ui.theme.BgColor
import com.example.mockupscreenshots.ui.theme.SecondaryColor

@Composable
fun ProjectPage(project: Project, onBackPressed: () -> Unit) {
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
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFf3f3f3))
                    .clickable(onClick = onBackPressed),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
            Text(
                modifier = Modifier.weight(1f),
                text = project.name,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = AppFonts,
                    textAlign = TextAlign.Center
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
                    painter = painterResource(id = R.drawable.ic_unselect_my_projects),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color.White)
                .padding(10.dp)
        ) {
            Row(modifier = Modifier) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = project.name,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = project.device,
                        style = TextStyle(
                            color = SecondaryColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
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
                        painter = painterResource(id = R.drawable.ic_edit),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
            Text(
                text = project.description,
                style = TextStyle(
                    color = SecondaryColor,
                    fontSize = 16.sp
                )
            )
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            AppButton(
                modifier = Modifier.weight(1f),
                buttonText = "Export",
                onClick = {

                }
            )
            AppButton(
                modifier = Modifier.weight(1f),
                buttonText = "Add Screenshot",
                onClick = {

                }
            )
        }
    }
}