package com.mobileappxperts.mockupgenerator.mockupmaker.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mobileappxperts.mockupgenerator.mockupmaker.R
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
            .background(color = AppColor)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_splash),
            contentDescription = "Splash Bg",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(id = R.drawable.ic_splash_text),
            contentDescription = "Splash Text",
            modifier = Modifier
                .padding(top = 100.dp, start = 30.dp, end = 30.dp)
                .fillMaxWidth()
                .height(80.dp),
            contentScale = ContentScale.FillBounds
        )
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Image(
//                modifier = Modifier
//                    .size(200.dp),
//                painter = painterResource(id = R.drawable.splash_logo),
//                contentDescription = null
//            )
//            Text(
//                modifier = Modifier.padding(top = 10.dp),
//                text = stringResource(id = R.string.app_name),
//                style = TextStyle(
//                    color = Color.Black,
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Bold,
//                    fontFamily = AppFonts
//                )
//            )
//        }
    }
}