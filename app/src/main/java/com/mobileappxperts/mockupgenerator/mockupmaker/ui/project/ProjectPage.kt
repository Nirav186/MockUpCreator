package com.mobileappxperts.mockupgenerator.mockupmaker.ui.project

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mobileappxperts.mockupgenerator.mockupmaker.R
import com.mobileappxperts.mockupgenerator.mockupmaker.core.AdmobBanner
import com.mobileappxperts.mockupgenerator.mockupmaker.core.components.AppButton
import com.mobileappxperts.mockupgenerator.mockupmaker.core.components.DropdownMenuNoPaddingVertical
import com.mobileappxperts.mockupgenerator.mockupmaker.core.utils.saveAndShareZip
import com.mobileappxperts.mockupgenerator.mockupmaker.core.utils.shareFile
import com.mobileappxperts.mockupgenerator.mockupmaker.data.model.Project
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.AppFonts
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.BgColor
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.SecondaryColor
import java.io.File


@Composable
fun ProjectPage(
    navController: NavHostController,
    projectId: Long,
    onBackPressed: () -> Unit,
    onAddScreenshotClick: (Project) -> Unit,
    onEdit: (Project) -> Unit,
    onImagePreview: (String) -> Unit
) {
    val projectViewModel: ProjectViewModel = hiltViewModel()
    val context = LocalContext.current
    val result =
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("filePath")
            ?.observeAsState()

    projectViewModel.getProjectById(projectId = projectId)

    LaunchedEffect(key1 = true) {
        result?.value?.let {
            Log.e("TAG000", "CreateProject: " + result.value)
            projectViewModel.getProjectById(projectId = projectId)
            if (File(it).exists()) {
                Log.e("TAG000", "ProjectPage: exists==>" + result.value)
                if (projectViewModel.project.screenshots.contains(it).not()) {
                    projectViewModel.project.screenshots.add(it)
                    projectViewModel.addProject(projectViewModel.project)
                }
            }
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
                    .size(36.dp)
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
                    .alpha(0f)
                    .size(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFf3f3f3)), contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.ic_delete_1),
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
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = AppFonts
                        )
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = projectViewModel.project.device,
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
                        .size(40.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .clickable(onClick = { onEdit(projectViewModel.project) })
                        .background(Color(0xFFf3f3f3)), contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxSize(),
                        painter = painterResource(id = R.drawable.ic_edit),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
            Text(
                text = projectViewModel.project.description, style = TextStyle(
                    color = SecondaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = AppFonts
                )
            )
        }
        AdmobBanner(modifier = Modifier.fillMaxWidth())
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            AppButton(modifier = Modifier.weight(1f), buttonText = "Export", onClick = {
                if (projectViewModel.project.screenshots.isNotEmpty()) {
                    context.saveAndShareZip(
                        screenshots = projectViewModel.project.screenshots,
                        zipName = projectViewModel.project.name
                    )
                }
            })
            AppButton(modifier = Modifier.weight(1f),
                buttonText = "Add Screenshot",
                onClick = { onAddScreenshotClick(projectViewModel.project) })
        }

        LazyVerticalGrid(modifier = Modifier
            .padding(horizontal = 20.dp)
            .weight(1f),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            content = {
                items(projectViewModel.project.screenshots) { screenshot ->
                    ScreenShotPreview(screenshot = screenshot, onImagePreview = onImagePreview)
                }
            })
    }
}

@Composable
private fun ScreenShotPreview(
    screenshot: String, onImagePreview: (String) -> Unit
) {
    val projectViewModel: ProjectViewModel = hiltViewModel()
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.wrapContentSize()) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp)),
            model = screenshot,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Box(modifier = Modifier.align(Alignment.TopEnd)) {
            Image(
                modifier = Modifier
                    .padding(8.dp)
                    .size(28.dp)
                    .clip(CircleShape)
                    .layoutId("popupAnchor")
                    .clickable(onClick = {
                        expanded = !expanded
                    })
                    .align(Alignment.TopEnd),
                painter = painterResource(id = R.drawable.ic_more),
                contentDescription = null,
            )
            MaterialTheme(shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(16.dp))) {
                DropdownMenuNoPaddingVertical(
                    modifier = Modifier
                        .align(Alignment.TopEnd),
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        onClick = { onImagePreview(screenshot) }
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_view),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "View")
                    }
                    DropdownMenuItem(onClick = { context.shareFile(File(screenshot)) }) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_send_2),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "Share")
                    }
                    DropdownMenuItem(onClick = {
                        val isDeleted =
                            projectViewModel.project.screenshots.remove(screenshot)
                        if (isDeleted) {
                            Log.e(
                                "TAG000",
                                "ProjectPage: isDeleted==>$screenshot"
                            )
                            File(screenshot).delete()
                            projectViewModel.addProject(projectViewModel.project)
                            projectViewModel.getProjectById(projectViewModel.project.projectId)
                            Toast
                                .makeText(
                                    context,
                                    "Deleted successfully",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                    }) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_delete_2),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "Delete")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ActionPopup() {
    Box(
        modifier = Modifier
            .width(100.dp)
            .padding(10.dp)
            .background(Color.White)
    ) {
        Column {
            Text(text = "View")
            Text(text = "Share")
            Text(text = "Delete")
        }
    }
}

