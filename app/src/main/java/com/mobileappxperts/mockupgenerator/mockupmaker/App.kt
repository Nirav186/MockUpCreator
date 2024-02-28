package com.mobileappxperts.mockupgenerator.mockupmaker

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.nirav.commons.ads.CommonAdManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
//        AppOpenAdManager(this, getString(R.string.app_open_id))
    }

    fun getAdsFromRemoteConfig(activity: Activity, onAdsInitialized: () -> Unit) {
        FirebaseApp.initializeApp(this)

        val remoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings =
            FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(10000).build()
        remoteConfig.setConfigSettingsAsync(configSettings)
        val jsonConfigKey = if (BuildConfig.DEBUG) "test_ids" else "real_ids"

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val json = FirebaseRemoteConfig.getInstance().getString(jsonConfigKey)
                    if (json.isNotEmpty()) {
                        CommonAdManager.initWithGdpr(
                            activity = activity,
                            application = this,
                            jsonString = json,
                            onAdsInitialized = onAdsInitialized
                        )
                    }
                } else {
                    Log.e("TAG", "Error occurred")
                }
            }
    }

}