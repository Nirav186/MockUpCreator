package com.mobileappxperts.mockupgenerator.mockupmaker.ui.splash

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mobileappxperts.mockupgenerator.mockupmaker.App
import com.mobileappxperts.mockupgenerator.mockupmaker.R
import com.mobileappxperts.mockupgenerator.mockupmaker.core.AppOpenAdManager
import com.mobileappxperts.mockupgenerator.mockupmaker.navigation.NavigationTarget
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.AppColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    var startAnimation by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(3000)
        navController.navigate(NavigationTarget.Home.route) {
            popUpTo(NavigationTarget.Splash.route) {
                inclusive = true
            }
        }
    }
    Splash()
}

@Preview
@Composable
fun Splash() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_splash),
            contentDescription = "Splash Bg",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(id = R.drawable.text_splash),
            contentDescription = "Splash Text",
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp, bottom = 80.dp)
                .fillMaxWidth()
                .aspectRatio(2.212f)
                .align(Alignment.Center),
            contentScale = ContentScale.FillBounds
        )
    }
}