package com.example.mockupscreenshots.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mockupscreenshots.R
import com.example.mockupscreenshots.core.getImageFromAsset
import com.example.mockupscreenshots.data.model.DeviceFrameItem
import com.example.mockupscreenshots.ui.DeviceFrameViewModel
import com.example.mockupscreenshots.ui.theme.AppColor
import com.example.mockupscreenshots.ui.theme.BgColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(onFrameSelect: (String) -> Unit) {
    val deviceFrameViewModel = hiltViewModel<DeviceFrameViewModel>()
    val frames = deviceFrameViewModel.state.frameItems

    Column(Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            text = "Select Frame",
            textAlign = TextAlign.Center,
            color = Color.Black,
            style = TextStyle(
                fontWeight = FontWeight.Bold, fontSize = 22.sp
            )
        )
        LazyVerticalGrid(modifier = Modifier,
            cells = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            content = {
                items(frames.size) {
                    EmptyFrame(frameItem = frames[it]) {
                        onFrameSelect(it.frameId)
                    }
                }
            })
    }
}

@Composable
private fun EmptyFrame(frameItem: DeviceFrameItem, onFrameSelect: (DeviceFrameItem) -> Unit) {
    val context = LocalContext.current
    context.getImageFromAsset(frameItem.frameId)?.let { bitmap ->
        Image(
            modifier = Modifier
                .height(150.dp)
                .clickable(onClick = { onFrameSelect(frameItem) }),
            bitmap = bitmap,
            contentDescription = "Frame",
            contentScale = ContentScale.Fit
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home(onNewProject: () -> Unit) {
    val selectedTab = remember {
        mutableStateOf(0)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BgColor)
    ) {
        if (selectedTab.value == 0) {
            HomeTab()
        } else {
            MyProjectsTab()
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .height(74.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(40.dp))
                .background(color = AppColor)
                .padding(horizontal = 36.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TabHome(selectedTab)
            TabMyProjects(selectedTab)
        }
        Card(
            modifier = Modifier
                .padding(bottom = 56.dp)
                .size(68.dp)
                .clip(CircleShape)
                .background(color = Color.White)
                .align(Alignment.BottomCenter),
            elevation = 10.dp,
            onClick = onNewProject
        ) {
            Icon(
                modifier = Modifier
                    .padding(14.dp)
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_add_project),
                contentDescription = null,
                tint = AppColor
            )
        }
    }
}

@Composable
fun TabHome(selectedPos: MutableState<Int>) {
    Column(
        modifier = Modifier.clickable(onClick = {
            selectedPos.value = 0
        }),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = if (selectedPos.value == 0)
                painterResource(id = R.drawable.ic_select_home)
            else
                painterResource(id = R.drawable.ic_unselect_home),
            contentDescription = null,
            tint = if (selectedPos.value == 0)
                Color.Black
            else
                Color(0xFF333333).copy(alpha = 0.5f)
        )
        Text(
            text = stringResource(id = R.string.home),
            color = if (selectedPos.value == 0)
                Color.Black
            else
                Color(0xFF333333).copy(alpha = 0.5f)
        )
    }
}

@Composable
fun TabMyProjects(selectedPos: MutableState<Int>) {
    Column(
        modifier = Modifier.clickable(onClick = {
            selectedPos.value = 1
        }),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = if (selectedPos.value == 1)
                painterResource(id = R.drawable.ic_select_my_projects)
            else
                painterResource(id = R.drawable.ic_unselect_my_projects),
            contentDescription = null,
            tint = if (selectedPos.value == 1)
                Color.Black
            else
                Color(0xFF333333).copy(alpha = 0.5f)
        )
        Text(
            text = stringResource(id = R.string.my_projects),
            color = if (selectedPos.value == 1)
                Color.Black
            else
                Color(0xFF333333).copy(alpha = 0.5f)
        )
    }
}