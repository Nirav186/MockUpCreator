package com.example.mockupscreenshots.ui.preview

import android.app.Activity
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import coil.compose.AsyncImage
import com.example.mockupscreenshots.R
import com.example.mockupscreenshots.core.utils.copyFile
import java.io.File

@Composable
fun FullScreenImageView(
    imagePath: String,
    onBackPressed: () -> Unit
) {
    val view = LocalView.current
    SideEffect {
        requestFullScreen(view)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .background(color = Color.Transparent)
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .align(Alignment.TopCenter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFf3f3f3).copy(0.2f))
                    .clickable(onClick = onBackPressed),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    tint = Color.White.copy(0.6f)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
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
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
                .clickable(onClick = {}),
            model = imagePath,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun FullScreenHomeImageView(
    imagePath: String,
    onBackPressed: () -> Unit
) {
    val view = LocalView.current
    SideEffect {
        requestFullScreen(view)
    }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .background(color = Color.Transparent)
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .align(Alignment.TopCenter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFf3f3f3).copy(0.2f))
                    .clickable(onClick = onBackPressed),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    tint = Color.White.copy(0.6f)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
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
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
                .clickable(onClick = {}),
            model = imagePath,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Row(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.BottomEnd),
            horizontalArrangement = Arrangement.End,
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier
                    .size(44.dp)
                    .clickable(onClick = {
                        val destPath =
                            Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES
                            ).absolutePath +
                                    File.separator +
                                    context.getString(R.string.app_name)
                        copyFile(
                            inputFile = imagePath,
                            outputPath = destPath
                        )
                        Toast
                            .makeText(
                                context,
                                "Downloaded at $destPath",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }),
                painter = painterResource(id = R.drawable.ic_download_2),
                contentDescription = null,
                tint = Color.Gray
            )
            Icon(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(44.dp)
                    .clickable(onClick = {
                        onBackPressed()
                        File(imagePath).delete()
                        Toast
                            .makeText(
                                context,
                                "Deleted successfully",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }),
                painter = painterResource(id = R.drawable.ic_delete_2),
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

private fun requestFullScreen(view: View) {
    val window = (view.context as Activity).window
    WindowCompat.getInsetsController(window, view).hide(
        WindowInsetsCompat.Type.statusBars() or
                WindowInsetsCompat.Type.navigationBars()
    )
}