package com.example.mockupscreenshots.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mockupscreenshots.core.ext.composableWithArgs
import com.example.mockupscreenshots.core.ext.getString
import com.example.mockupscreenshots.data.model.Project
import com.example.mockupscreenshots.ui.addscreenshot.AddScreenshot
import com.example.mockupscreenshots.ui.home.Home
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
            })
        }
        composable(route = NavigationTarget.CreateProject.route) {
            CreateProject(
                navController = navController,
                onAddScreenshotClick = {
                    navController.navigate(NavigationTarget.AddScreenshot.route)
                }
            )
        }
        composableWithArgs(route = NavigationTarget.ProjectPage.route, "project") {
            val project = Gson().fromJson(it.getString("project"), Project::class.java)
            ProjectPage(
                navController = navController,
                project = project,
                onBackPressed = { navController.popBackStack() },
                onAddScreenshotClick = {
                    navController.navigate(NavigationTarget.AddScreenshot.route)
                }
            )
        }
        composable(route = NavigationTarget.AddScreenshot.route) {
            AddScreenshot(navController)
        }
    }
}