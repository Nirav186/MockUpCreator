package com.mobileappxperts.mockupgenerator.mockupmaker.ui.home

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobileappxperts.mockupgenerator.mockupmaker.R
import com.mobileappxperts.mockupgenerator.mockupmaker.core.AppOpenAdManager
import com.mobileappxperts.mockupgenerator.mockupmaker.core.utils.Constants
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.HomeFrame
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.Project
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.AppColor
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.BgColor
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.TabTextStyle

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home(
    onNewProject: () -> Unit,
    onProjectSelect: (Project) -> Unit,
    onHomeFrameClick: (HomeFrame) -> Unit,
    onImagePreview: (String) -> Unit,
    onSettingsClick: () -> Unit
) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val context = LocalContext.current

    if (Constants.isFirstTimeAppOpenShow && AppOpenAdManager.appOpenAd != null && Constants.isAppOpenAdEnabled) {
        AppOpenAdManager.appOpenAd?.show(context as ComponentActivity)
        Constants.isFirstTimeAppOpenShow = false
    }

    Column {
        Box(
            modifier = Modifier
                .weight(1f)
                .background(color = BgColor)
        ) {
            if (homeViewModel.selectedTab.value == 0) {
                HomeTabContent(
                    onHomeFrameClick = onHomeFrameClick,
                    onImagePreview = onImagePreview,
                    onSettingsClick = onSettingsClick
                )
            } else {
                MyProjectsTabContent(onProjectSelect = onProjectSelect)
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
                TabHome(homeViewModel.selectedTab)
                TabMyProjects(homeViewModel.selectedTab)
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
}

@Composable
fun TabHome(selectedPos: MutableState<Int>) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = {
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
                Color(0xFF333333).copy(alpha = 0.5f),
            style = TabTextStyle
        )
    }
}

@Composable
fun TabMyProjects(selectedPos: MutableState<Int>) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = {
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
                Color(0xFF333333).copy(alpha = 0.5f),
            style = TabTextStyle
        )
    }
}