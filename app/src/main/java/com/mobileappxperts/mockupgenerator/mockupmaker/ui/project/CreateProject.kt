package com.mobileappxperts.mockupgenerator.mockupmaker.ui.project

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mobileappxperts.mockupgenerator.mockupmaker.R
import com.mobileappxperts.mockupgenerator.mockupmaker.core.AdManager
import com.mobileappxperts.mockupgenerator.mockupmaker.core.components.AppButton
import com.mobileappxperts.mockupgenerator.mockupmaker.core.components.CustomTextField
import com.mobileappxperts.mockupgenerator.mockupmaker.core.components.DropDownMenu
import com.mobileappxperts.mockupgenerator.mockupmaker.core.components.dashedBorder
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.Project
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.AppFonts
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.BgColor
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.SecondaryColor

@Composable
fun CreateProject(
    navController: NavHostController,
    project: Project?,
    onAddScreenshotClick: () -> Unit
) {

    val createViewModel: CreateViewModel = hiltViewModel()
    val projectViewModel: ProjectViewModel = hiltViewModel()
    LaunchedEffect(key1 = true, block = {
        project?.let {
            createViewModel.projectName = project.name
            createViewModel.device = project.device
            createViewModel.projectDes = project.description
            createViewModel.screenshots = project.screenshots
            createViewModel.projectId = project.projectId
        }
    })
    val context = LocalContext.current

    val result = navController
        .currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<String>("filePath")?.observeAsState()

    result?.value?.let {
        Log.e("TAG000", "CreateProject: " + result.value)
        if (createViewModel.screenshots.contains(it).not()) {
            createViewModel.screenshots.add(it)
        }
    }

    BackHandler {
        navController.navigateUp()
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
            Text(
                modifier = Modifier.weight(1f),
                text = "Create Project",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = AppFonts
                )
            )
        }
        CustomTextField(
            value = createViewModel.projectName,
            onValueChange = {createViewModel.projectName = it},
            placeholderText = "Project Name"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 20.dp)
                .border(
                    width = 2.dp, color = Color(0xFFD2E0ED),
                    shape = RoundedCornerShape(14.dp)
                )
        ) {
            TextField(
                value = createViewModel.projectDes,
                onValueChange = {
                    createViewModel.projectDes = it
                }, placeholder = {
                    Text(
                        "Project Description", color = Color.Gray
                    )
                }, colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Gray,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
        DropDownMenu(
            modifier = Modifier,
            items = listOf("Android", "iOS"),
            selectedItem = createViewModel.device,
            onItemSelect = {
                createViewModel.device = it
            }
        )
        LazyVerticalGrid(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .weight(1f),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            content = {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .dashedBorder(1.dp, Color(0xFFB1BCC7), 8.dp)
                            .clickable(onClick = onAddScreenshotClick)
                            .padding(horizontal = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(32.dp)
                                .fillMaxSize(),
                            painter = painterResource(id = R.drawable.ic_add_project),
                            contentDescription = null,
                            tint = SecondaryColor
                        )
                    }
                }
                items(createViewModel.screenshots) {
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
        AppButton(modifier = Modifier
            .height(80.dp)
            .width(200.dp),
            buttonText = "Save",
            onClick = {
                saveProject(
                    context = context,
                    project = Project(
                        projectId = createViewModel.projectId,
                        name = createViewModel.projectName,
                        description = createViewModel.projectDes,
                        device = createViewModel.device,
                        screenshots = createViewModel.screenshots
                    ),
                    projectViewModel = projectViewModel,
                    navController = navController
                )
            })
    }
}

fun saveProject(
    context: Context,
    project: Project,
    projectViewModel: ProjectViewModel,
    navController: NavHostController
) {
    if (project.name.trim().isEmpty()) {
        Toast.makeText(context, "Please Enter Project Name", Toast.LENGTH_SHORT).show()
        return
    }
    if (project.description.isEmpty()) {
        Toast.makeText(context, "Please Enter Project Description", Toast.LENGTH_SHORT).show()
        return
    }
    AdManager.showInterstitialAd(context as ComponentActivity)
    projectViewModel.addProject(project)
    navController.navigateUp()
}