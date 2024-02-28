package com.mobileappxperts.mockupgenerator.mockupmaker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.mobileappxperts.mockupgenerator.mockupmaker.core.utils.Constants
import com.mobileappxperts.mockupgenerator.mockupmaker.navigation.NavigationGraph
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.MockUpScreenShotsTheme
import com.nirav.commons.CommonGdprDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App().getAdsFromRemoteConfig(this) {
            //showBannerAd(binding.adView)
        }
        setContent {
            MockUpApp()
        }
    }

    @Composable
    private fun MockUpApp() {
        val navController = rememberNavController()
        MockUpScreenShotsTheme {
            Surface(
                modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
            ) {
                NavigationGraph(navController = navController)
//                    FullMockUps()
//                    InclineMockUp()
//                    HalfMockUp()
//                    MockUp1()
            }
        }
    }
}