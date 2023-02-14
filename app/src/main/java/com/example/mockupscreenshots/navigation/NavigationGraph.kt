package com.example.mockupscreenshots.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mockupscreenshots.core.ext.composableWithArgs
import com.example.mockupscreenshots.core.ext.getString
import com.example.mockupscreenshots.core.utils.AdManager
import com.example.mockupscreenshots.core.utils.Constants
import com.example.mockupscreenshots.data.model.HomeFrame
import com.example.mockupscreenshots.data.model.Project
import com.example.mockupscreenshots.ui.addscreenshot.AddScreenshot
import com.example.mockupscreenshots.ui.deviceframe.FullMockUps
import com.example.mockupscreenshots.ui.home.Home
import com.example.mockupscreenshots.ui.preview.FullScreenHomeImageView
import com.example.mockupscreenshots.ui.preview.FullScreenImageView
import com.example.mockupscreenshots.ui.project.CreateProject
import com.example.mockupscreenshots.ui.project.ProjectPage
import com.example.mockupscreenshots.ui.settings.SettingScreen
import com.example.mockupscreenshots.ui.splash.SplashScreen
import com.google.gson.Gson
import com.yodo1.mas.interstitial.Yodo1MasInterstitialAd

@Composable
fun NavigationGraph(navController: NavHostController) {
    val activity = LocalContext.current as Activity
    NavHost(
        navController = navController, startDestination = NavigationTarget.Splash.route
    ) {
        composable(route = NavigationTarget.Splash.route) {
            SplashScreen(navController)
        }
        composable(route = NavigationTarget.Home.route) {
            Home(onNewProject = {
                navController.navigate(NavigationTarget.CreateProject.route)
            }, onProjectSelect = { project ->
                navController.navigate(buildProjectPageRoute(project))
            }, onHomeFrameClick = { homeFrame ->
                navController.navigate(buildAddScreenshotRoute(homeFrame, null))
            }, onImagePreview = {
                navController.navigate(buildHomeImagePreviewRoute(it))
            }, onSettingsClick = {
                navController.navigate(NavigationTarget.Settings.route)
            })
        }
        composable(route = NavigationTarget.CreateProject.route) {
            val project = Gson().fromJson(it.getString("project"), Project::class.java)
            CreateProject(
                navController = navController,
                project = project,
                onAddScreenshotClick = {
                    navController.navigate(NavigationTarget.AddScreenshot.route)
                }
            )
        }
        composableWithArgs(route = NavigationTarget.ProjectPage.route, "project") {
            val project = Gson().fromJson(it.getString("project"), Project::class.java)
            ProjectPage(
                navController = navController,
                projectId = project.projectId,
                onBackPressed = {
                    onBack(navController, activity)
                },
                onAddScreenshotClick = { project ->
                    navController.navigate(
                        buildAddScreenshotRoute(
                            homeFrame = null,
                            project = project
                        )
                    )
                },
                onEdit = { project ->
                    navController.navigate(buildCreateProjectRoute(project))
                },
                onImagePreview = { imagePath ->
                    navController.navigate(buildImagePreviewRoute(imagePath))
                }
            )
        }
        composableWithArgs(
            route = NavigationTarget.AddScreenshot.route,
            arguments = arrayOf("homeFrame", "project")
        ) {
            val homeFrame = Gson().fromJson(it.getString("homeFrame"), HomeFrame::class.java)
            val project = Gson().fromJson(it.getString("project"), Project::class.java)
            AddScreenshot(navController, homeFrame, project)
        }
        composable(route = NavigationTarget.Temp.route) {
            FullMockUps()
        }
        composableWithArgs(route = NavigationTarget.ImagePreview.route, "imagePath") {
            it.getString("imagePath")?.let { imagePath ->
                FullScreenImageView(
                    imagePath = imagePath,
                    onBackPressed = {
                        onBack(navController, activity)
                    })
            }
        }
        composableWithArgs(route = NavigationTarget.HomeImagePreview.route, "imagePath") {
            it.getString("imagePath")?.let { imagePath ->
                FullScreenHomeImageView(
                    imagePath = imagePath,
                    onBackPressed = {
                        onBack(navController, activity)
                    })
            }
        }
        composable(route = NavigationTarget.Settings.route) {
            SettingScreen {
                navController.popBackStack()
            }
        }
    }
}

fun onBack(navController: NavController, activity: Activity) {
    val isLoaded = Yodo1MasInterstitialAd.getInstance().isLoaded
    if (Constants.isInterReadyToShow && isLoaded) {
        AdManager().showInterstitial(activity)
    }
    navController.popBackStack()
}