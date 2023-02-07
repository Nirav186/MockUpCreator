package com.example.mockupscreenshots.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.mockupscreenshots.data.model.HomeFrame
import com.example.mockupscreenshots.ui.DeviceFrameViewModel

@Composable
fun HomeTab() {
    val deviceFrameViewModel = hiltViewModel<DeviceFrameViewModel>()
    val frames = deviceFrameViewModel.state.homeFrames
    Column(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            content = {
                items(frames) {
                    HomeFrameItem(frame = it) {

                    }
                }
            })
    }
}

@Composable
fun HomeFrameItem(frame: HomeFrame, onClick: () -> Unit) {
    AsyncImage(
        modifier = Modifier
            .clickable(onClick = onClick)
            .height(200.dp)
            .wrapContentWidth()
            .clip(RoundedCornerShape(10.dp)),
        model = frame.imageUrl,
        contentDescription = null,
        contentScale = ContentScale.Fit
    )
}