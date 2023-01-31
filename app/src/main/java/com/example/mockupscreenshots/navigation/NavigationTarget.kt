package com.example.mockupscreenshots.navigation

import android.net.Uri

sealed class NavigationTarget(var route: String) {
    object Home : NavigationTarget("app://home")
    object ScreenShotSelectionPage : NavigationTarget("app://screenShotSelectionPage?frameId={frameId}")
    object EditScreenShot : NavigationTarget("app://editScreenShot")
    object CreateProject : NavigationTarget("app://createProject")
}

fun buildScreenShotSelectionRoute(frameId: String): String {
    return Uri.Builder()
        .scheme("app")
        .authority("screenShotSelectionPage")
        .appendQueryParameter("frameId", frameId)
        .build()
        .toString()
}
