package com.mobileappxperts.mockupgenerator.mockupmaker.navigation

import android.net.Uri
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.HomeFrame
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.Project
import com.google.gson.Gson

sealed class NavigationTarget(var route: String) {
    object Splash : NavigationTarget("app://splash")
    object Home : NavigationTarget("app://home")
    object EditScreenShot : NavigationTarget("app://editScreenShot")
    object CreateProject : NavigationTarget("app://createProject?project={project}")
    object ProjectPage : NavigationTarget("app://projectPage?project={project}")

    //    object AddScreenshot :
//        NavigationTarget("app://addScreenshot?homeFrame={homeFrame}?project={project}")
    object AddScreenshot :
        NavigationTarget("app://addScreenshot?homeFrame={homeFrame},project={project}")

    object ImagePreview : NavigationTarget("app://imagePreview?imagePath={imagePath}")
    object HomeImagePreview : NavigationTarget("app://homeImagePreview?imagePath={imagePath}")
    object Settings : NavigationTarget("app://settings")
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

fun buildImagePreviewRoute(imagePath: String): String {
    return Uri.Builder()
        .scheme("app")
        .authority("imagePreview")
        .appendQueryParameter("imagePath", imagePath)
        .build()
        .toString()
}

fun buildHomeImagePreviewRoute(imagePath: String): String {
    return Uri.Builder()
        .scheme("app")
        .authority("homeImagePreview")
        .appendQueryParameter("imagePath", imagePath)
        .build()
        .toString()
}

fun buildAddScreenshotRoute(
    homeFrame: HomeFrame? = null,
    project: Project? = null
): String {
    return "app://addScreenshot?homeFrame=${Gson().toJson(homeFrame)},project=${Gson().toJson(project)}"
}
