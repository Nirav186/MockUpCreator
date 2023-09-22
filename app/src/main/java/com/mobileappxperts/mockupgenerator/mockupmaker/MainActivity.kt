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
import com.mobileappxperts.mockupgenerator.mockupmaker.core.AdManager
import com.mobileappxperts.mockupgenerator.mockupmaker.core.utils.Constants
import com.mobileappxperts.mockupgenerator.mockupmaker.navigation.NavigationGraph
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.MockUpScreenShotsTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AdManager.initAds(this)
        getDataFromRemoteConfig()
        setContent {
            MockUpApp()
        }
    }

    private fun getDataFromRemoteConfig() {
        val mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings =
            FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(0)
                .build()
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

        mFirebaseRemoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Constants.isAppOpenAdEnabled = mFirebaseRemoteConfig.getBoolean("appOpenAd")
                    Constants.isBannerAdEnabled = mFirebaseRemoteConfig.getBoolean("bannerAd")
                    Constants.isIntertitialAdEnabled = mFirebaseRemoteConfig.getBoolean("intertitialAd")
                    Constants.isRewardAdEnabled = mFirebaseRemoteConfig.getBoolean("rewardAd")
                } else {
                    Log.d("", "")
                }
            }
            .addOnFailureListener {
                Log.e("TAG1112", "getDataFromRemoteConfig: " + it.message)
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