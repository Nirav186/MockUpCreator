package com.example.mockupscreenshots.navigation

import android.net.Uri
import com.example.mockupscreenshots.data.model.Project
import com.google.gson.Gson

sealed class NavigationTarget(var route: String) {
    object Home : NavigationTarget("app://home")
    object ScreenShotSelectionPage :
        NavigationTarget("app://screenShotSelectionPage?frameId={frameId}")

    object EditScreenShot : NavigationTarget("app://editScreenShot")
    object CreateProject : NavigationTarget("app://createProject")
    object ProjectPage : NavigationTarget("app://projectPage?project={project}")
}

fun buildScreenShotSelectionRoute(frameId: String): String {
    return Uri.Builder()
        .scheme("app")
        .authority("screenShotSelectionPage")
        .appendQueryParameter("frameId", frameId)
        .build()
        .toString()
}

fun buildProjectPageRoute(project: Project): String {
    return Uri.Builder()
        .scheme("app")
        .authority("projectPage")
        .appendQueryParameter("project", Gson().toJson(project))
        .build()
        .toString()
}
