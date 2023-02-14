package com.example.mockupscreenshots.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mockupscreenshots.R
import com.example.mockupscreenshots.navigation.NavigationTarget
import com.example.mockupscreenshots.ui.theme.AppFonts
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
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_splash),
            contentDescription = "Splash Bg",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(Color.White)
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier
                    .size(200.dp),
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = stringResource(id = R.string.app_name),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = AppFonts
                )
            )
        }
    }
}