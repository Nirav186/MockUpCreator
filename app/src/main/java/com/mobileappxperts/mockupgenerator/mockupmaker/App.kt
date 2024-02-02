package com.mobileappxperts.mockupgenerator.mockupmaker

import android.app.Application
import android.content.Context
import com.mobileappxperts.mockupgenerator.mockupmaker.core.AppOpenAdManager
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

}