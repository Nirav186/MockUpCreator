package com.example.mockupscreenshots

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.mockupscreenshots.navigation.NavigationGraph
import com.example.mockupscreenshots.ui.theme.MockUpScreenShotsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MockUpApp()
        }
    }

    @Composable
    private fun MockUpApp() {
        val navController = rememberNavController()
        MockUpScreenShotsTheme {
            Surface(
                modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
            ) {
                NavigationGraph(navController = navController)
//                    FullMockUps()
//                    InclineMockUp()
//                    HalfMockUp()
//                    MockUp1()
            }
        }
    }
}