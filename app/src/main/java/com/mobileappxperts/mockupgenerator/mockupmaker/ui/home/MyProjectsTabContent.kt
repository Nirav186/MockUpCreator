package com.mobileappxperts.mockupgenerator.mockupmaker.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobileappxperts.mockupgenerator.mockupmaker.R
import com.mobileappxperts.mockupgenerator.mockupmaker.core.components.NativeBanner
import com.mobileappxperts.mockupgenerator.mockupmaker.core.utils.saveAndShareZip
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.Project
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.project.ProjectUiState
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.project.ProjectViewModel
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.AppColor
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.AppFonts
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.BgColor
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.SecondaryColor

@Composable
fun MyProjectsTabContent(onProjectSelect: (Project) -> Unit) {
    val projectViewModel: ProjectViewModel = hiltViewModel()

    val uiState = projectViewModel.uiState.collectAsState().value

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
                .padding(vertical = 20.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "My Projects",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = AppFonts
                )
            )
            Box(
                modifier = Modifier
                    .alpha(if (projectViewModel.selectedProjects.isEmpty()) 0f else 1f)
                    .size(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFf3f3f3))
                    .clickable(onClick = {
                        projectViewModel.deleteSelectedProjects()
                    }),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.ic_delete_1),
                    contentDescription = null,
                    tint = Color.Red
                )
            }
        }
        when (uiState) {
            is ProjectUiState.MyProjectData -> {
                val projects = uiState.projects
                projects?.let {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(0.dp)
                    ) {
                        if (projects.isNotEmpty()) {
                            item {
                                NativeBanner()
                            }
                        }
                        items(projects) { project ->
                            ProjectCard(
                                project = project,
                                isSelected = projectViewModel.selectedProjects.contains(project),
                                onClick = { onProjectSelect(project) },
                                onLongClick = {
                                    if (projectViewModel.selectedProjects.contains(project)) {
                                        projectViewModel.selectedProjects.remove(project)
                                    } else {
                                        projectViewModel.selectedProjects.add(project)
                                    }
                                })
                        }
                    }
                }
            }
            is ProjectUiState.Loading -> {}
            is ProjectUiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ProjectCard(
    project: Project,
    isSelected: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    val context = LocalContext.current
    val projectViewModel: ProjectViewModel = hiltViewModel()
    Box {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color.White)
                .combinedClickable(
                    onClick = {
                        if (projectViewModel.selectedProjects.isEmpty()) onClick()
                        else onLongClick()
                    },
                    onLongClick = onLongClick
                )
                .padding(10.dp)
        ) {
            Row(modifier = Modifier) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = project.name,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = AppFonts
                        )
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = project.device,
                        style = TextStyle(
                            color = SecondaryColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = AppFonts
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(AppColor)
                        .clickable(onClick = {
                            context.saveAndShareZip(
                                screenshots = project.screenshots,
                                zipName = project.name
                            )
                        }),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize(),
                        painter = painterResource(id = R.drawable.ic_send_2),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
            Text(
                text = project.description,
                style = TextStyle(
                    color = SecondaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = AppFonts
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        if (isSelected) {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(32.dp)
                    .align(Alignment.TopStart),
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = null,
                tint = AppColor
            )
        }
    }
}