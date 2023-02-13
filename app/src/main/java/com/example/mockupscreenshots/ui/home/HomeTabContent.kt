package com.example.mockupscreenshots.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.mockupscreenshots.R
import com.example.mockupscreenshots.core.ext.getHomeScreenshots
import com.example.mockupscreenshots.data.model.HomeFrame
import com.example.mockupscreenshots.ui.DeviceFrameViewModel
import com.example.mockupscreenshots.ui.theme.AppFonts
import com.example.mockupscreenshots.ui.theme.FredokaOne

@Composable
fun HomeTabContent(
    onHomeFrameClick: (HomeFrame) -> Unit,
    onImagePreview: (String) -> Unit,
    onSettingsClick: () -> Unit
) {
    val context = LocalContext.current
    val deviceFrameViewModel = hiltViewModel<DeviceFrameViewModel>()
    val frames = deviceFrameViewModel.state.homeFrames
    val homeScreenshots = context.getHomeScreenshots()
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 10.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "App Mockup",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FredokaOne
                )
            )
            Icon(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable(onClick = onSettingsClick)
                    .size(32.dp)
                    .padding(4.dp),
                painter = painterResource(id = R.drawable.ic_setting),
                contentDescription = null
            )
        }
        if (homeScreenshots.isNullOrEmpty().not()) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "My Screenshots",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = AppFonts
                )
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                content = {
                    items(homeScreenshots!!) {
                        AsyncImage(
                            modifier = Modifier
                                .height(120.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .clickable(onClick = {
                                    onImagePreview(it.absolutePath)
                                }),
                            model = it.absolutePath,
                            contentDescription = null,
                            contentScale = ContentScale.Fit
                        )
                    }
                })
        }
        Text(
            modifier = Modifier.padding(10.dp),
            text = "Templates",
            style = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = AppFonts
            )
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(start = 10.dp, end = 10.dp, bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            content = {
                items(frames) {
                    HomeFrameItem(
                        frame = it,
                        onClick = {
                            onHomeFrameClick(it)
                        }
                    )
                }
            })
    }
}

@Composable
fun HomeFrameItem(frame: HomeFrame, onClick: () -> Unit) {
    AsyncImage(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick),
        model = frame.imageUrl,
        contentDescription = null,
        contentScale = ContentScale.Fit
    )
}