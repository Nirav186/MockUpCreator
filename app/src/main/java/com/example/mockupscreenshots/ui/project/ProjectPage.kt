package com.example.mockupscreenshots.ui.project

import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.mockupscreenshots.R
import com.example.mockupscreenshots.core.components.AppButton
import com.example.mockupscreenshots.core.utils.copyFile
import com.example.mockupscreenshots.ui.theme.AppFonts
import com.example.mockupscreenshots.ui.theme.BgColor
import com.example.mockupscreenshots.ui.theme.SecondaryColor
import java.io.File

@Composable
fun ProjectPage(
    navController: NavHostController,
    projectId: Long,
    onBackPressed: () -> Unit,
    onAddScreenshotClick: () -> Unit,
    onEdit: () -> Unit
) {
    val projectViewModel: ProjectViewModel = hiltViewModel()
    val context = LocalContext.current
    val result = navController
        .currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<String>("filePath")?.observeAsState()

//    val projectResult = navController
//        .currentBackStackEntry
//        ?.savedStateHandle
//        ?.getLiveData<Project>("project")?.observeAsState()

    projectViewModel.getProjectById(projectId = projectId)

    result?.value?.let {
        Log.e("TAG000", "CreateProject: " + result.value)
        if (projectViewModel.project.screenshots.contains(it).not()) {
            projectViewModel.project.screenshots.add(it)
            projectViewModel.addProject(projectViewModel.project)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgColor),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(vertical = 10.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFf3f3f3))
                    .clickable(onClick = onBackPressed),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
            Text(
                modifier = Modifier.weight(1f),
                text = projectViewModel.project.name,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = AppFonts,
                    textAlign = TextAlign.Center
                )
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFf3f3f3)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color.White)
                .padding(10.dp)
        ) {
            Row(modifier = Modifier) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = projectViewModel.project.name,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = projectViewModel.project.device,
                        style = TextStyle(
                            color = SecondaryColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .clickable(onClick = onEdit)
                        .background(Color(0xFFf3f3f3)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize(),
                        painter = painterResource(id = R.drawable.ic_edit),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
            Text(
                text = projectViewModel.project.description,
                style = TextStyle(
                    color = SecondaryColor,
                    fontSize = 16.sp
                )
            )
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            AppButton(
                modifier = Modifier.weight(1f),
                buttonText = "Export",
                onClick = {
                    Log.e("TAG111", "ProjectPage: " + projectViewModel.project.screenshots[0])
                    Log.e(
                        "TAG111",
                        "ProjectPage: " + projectViewModel.project.screenshots[0].split("/").last()
                    )
                    val destPath =
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absolutePath +
                                File.separator + context.getString(R.string.app_name)
                    projectViewModel.project.screenshots.forEach {
                        copyFile(
                            inputFile = it,
                            outputPath = destPath
                        )
                    }
                    Toast.makeText(context, "Export successfully", Toast.LENGTH_SHORT).show()
                }
            )
            AppButton(
                modifier = Modifier.weight(1f),
                buttonText = "Add Screenshot",
                onClick = onAddScreenshotClick
            )
        }

        LazyVerticalGrid(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .weight(1f),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            content = {
                items(projectViewModel.project.screenshots) {
                    AsyncImage(
                        modifier = Modifier
                            .height(120.dp)
                            .padding(horizontal = 5.dp)
                            .clickable(onClick = {}),
                        model = it,
                        contentDescription = null
                    )
                }
            })
    }
}