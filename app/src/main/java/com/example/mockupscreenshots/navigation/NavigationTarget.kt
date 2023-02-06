package com.example.mockupscreenshots.navigation

import android.net.Uri
import com.example.mockupscreenshots.data.model.Project
import com.google.gson.Gson

sealed class NavigationTarget(var route: String) {
    object Home : NavigationTarget("app://home")
    object EditScreenShot : NavigationTarget("app://editScreenShot")
    object CreateProject : NavigationTarget("app://createProject?project={project}")
    object ProjectPage : NavigationTarget("app://projectPage?project={project}")
    object AddScreenshot : NavigationTarget("app://addScreenshot")
    object Temp : NavigationTarget("app://temp")
}

fun buildProjectPageRoute(project: Project): String {
    return Uri.Builder()
        .scheme("app")
        .authority("projectPage")
        .appendQueryParameter("project", Gson().toJson(project))
        .build()
        .toString()
}

fun buildCreateProjectRoute(project: Project): String {
    return Uri.Builder()
        .scheme("app")
        .authority("createProject")
        .appendQueryParameter("project", Gson().toJson(project))
        .build()
        .toString()
}
