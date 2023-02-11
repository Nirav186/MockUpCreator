package com.example.mockupscreenshots.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mockupscreenshots.core.ext.composableWithArgs
import com.example.mockupscreenshots.core.ext.getString
import com.example.mockupscreenshots.data.model.HomeFrame
import com.example.mockupscreenshots.data.model.Project
import com.example.mockupscreenshots.ui.FullMockUps
import com.example.mockupscreenshots.ui.addscreenshot.AddScreenshot
import com.example.mockupscreenshots.ui.home.Home
import com.example.mockupscreenshots.ui.preview.FullScreenHomeImageView
import com.example.mockupscreenshots.ui.preview.FullScreenImageView
import com.example.mockupscreenshots.ui.project.CreateProject
import com.example.mockupscreenshots.ui.project.ProjectPage
import com.google.gson.Gson

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = NavigationTarget.Home.route
    ) {
        composable(route = NavigationTarget.Home.route) {
            Home(onNewProject = {
                navController.navigate(NavigationTarget.CreateProject.route)
            }, onProjectSelect = { project ->
                navController.navigate(buildProjectPageRoute(project))
            }, onHomeFrameClick = { homeFrame ->
                navController.navigate(buildAddScreenshotRoute(homeFrame))
            }, onImagePreview = {
                navController.navigate(buildHomeImagePreviewRoute(it))
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
                onBackPressed = { navController.popBackStack() },
                onAddScreenshotClick = {
                    navController.navigate(NavigationTarget.AddScreenshot.route)
                },
                onEdit = {
                    navController.navigate(buildCreateProjectRoute(it))
                },
                onImagePreview = { imagePath ->
                    navController.navigate(buildImagePreviewRoute(imagePath))
                }
            )
        }
        composableWithArgs(route = NavigationTarget.AddScreenshot.route, "homeFrame") {
            val homeFrame = Gson().fromJson(it.getString("homeFrame"), HomeFrame::class.java)
            AddScreenshot(navController, homeFrame)
        }
        composable(route = NavigationTarget.Temp.route) {
            FullMockUps()
        }
        composableWithArgs(route = NavigationTarget.ImagePreview.route, "imagePath") {
            it.getString("imagePath")?.let { imagePath ->
                FullScreenImageView(
                    imagePath = imagePath,
                    onBackPressed = {
                        navController.navigateUp()
                    })
            }
        }
        composableWithArgs(route = NavigationTarget.HomeImagePreview.route, "imagePath") {
            it.getString("imagePath")?.let { imagePath ->
                FullScreenHomeImageView(
                    imagePath = imagePath,
                    onBackPressed = {
                        navController.navigateUp()
                    })
            }
        }
    }
}