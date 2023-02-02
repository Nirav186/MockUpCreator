package com.example.mockupscreenshots.navigation

import android.net.Uri
import com.example.mockupscreenshots.data.model.Project
import com.google.gson.Gson

sealed class NavigationTarget(var route: String) {
    object Home : NavigationTarget("app://home")
    object EditScreenShot : NavigationTarget("app://editScreenShot")
    object CreateProject : NavigationTarget("app://createProject")
    object ProjectPage : NavigationTarget("app://projectPage?project={project}")
    object AddScreenshot : NavigationTarget("app://addScreenshot")
}

fun buildProjectPageRoute(project: Project): String {
    return Uri.Builder()
        .scheme("app")
        .authority("projectPage")
        .appendQueryParameter("project", Gson().toJson(project))
        .build()
        .toString()
}
