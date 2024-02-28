package mockupmaker.screenshots.mockup.generator.navigation

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.gson.Gson
import mockupmaker.screenshots.mockup.generator.core.ext.composableWithArgs
import mockupmaker.screenshots.mockup.generator.core.ext.getString
import mockupmaker.screenshots.mockup.generator.data.model.HomeFrame
import mockupmaker.screenshots.mockup.generator.data.model.Project
import mockupmaker.screenshots.mockup.generator.ui.addscreenshot.AddScreenshot
import mockupmaker.screenshots.mockup.generator.ui.deviceframe.FullMockUps
import mockupmaker.screenshots.mockup.generator.ui.home.Home
import mockupmaker.screenshots.mockup.generator.ui.preview.FullScreenHomeImageView
import mockupmaker.screenshots.mockup.generator.ui.preview.FullScreenImageView
import mockupmaker.screenshots.mockup.generator.ui.project.CreateProject
import mockupmaker.screenshots.mockup.generator.ui.project.ProjectPage
import mockupmaker.screenshots.mockup.generator.ui.settings.SettingScreen
import mockupmaker.screenshots.mockup.generator.ui.splash.SplashScreen
import com.nirav.commons.ads.CommonAdManager.showInterstitialAd

@Composable
fun NavigationGraph(navController: NavHostController) {
    val activity = LocalContext.current as ComponentActivity
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
                },
                onBackPressed = {
                    onBack(navController, activity)
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
            AddScreenshot(
                navHostController = navController,
                homeFrame = homeFrame,
                project = project,
                onBackPressed = { onBack(navController, activity) })
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
            SettingScreen { onBack(navController, activity) }
        }
    }
}

fun onBack(navController: NavController, activity: Activity) {
    activity.showInterstitialAd()
    navController.popBackStack()
}