package com.mobileappxperts.mockupgenerator.mockupmaker.navigation

import android.net.Uri
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.HomeFrame
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.Project
import com.google.gson.Gson

sealed class NavigationTarget(var route: String) {
    data object Splash : NavigationTarget("app://splash")
    data object Home : NavigationTarget("app://home")
    data object EditScreenShot : NavigationTarget("app://editScreenShot")
    data object CreateProject : NavigationTarget("app://createProject?project={project}")
    data object ProjectPage : NavigationTarget("app://projectPage?project={project}")

    //    object AddScreenshot :
//        NavigationTarget("app://addScreenshot?homeFrame={homeFrame}?project={project}")
    data object AddScreenshot :
        NavigationTarget("app://addScreenshot?homeFrame={homeFrame},project={project}")

    data object ImagePreview : NavigationTarget("app://imagePreview?imagePath={imagePath}")
    data object HomeImagePreview : NavigationTarget("app://homeImagePreview?imagePath={imagePath}")
    data object Settings : NavigationTarget("app://settings")
    data object Temp : NavigationTarget("app://temp")
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
    return "app://addScreenshot?" +
            "homeFrame=${if (homeFrame == null) null else Gson().toJson(homeFrame)}," +
            "project=${if (project == null) null else Gson().toJson(project)}"
}
