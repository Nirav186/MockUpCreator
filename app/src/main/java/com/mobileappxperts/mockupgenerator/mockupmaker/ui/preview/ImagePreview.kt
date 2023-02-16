package com.mobileappxperts.mockupgenerator.mockupmaker.ui.preview

import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mobileappxperts.mockupgenerator.mockupmaker.R
import com.mobileappxperts.mockupgenerator.mockupmaker.core.utils.copyFile
import com.mobileappxperts.mockupgenerator.mockupmaker.core.utils.shareFile
import java.io.File

@Composable
fun FullScreenImageView(
    imagePath: String,
    onBackPressed: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
                .clickable(onClick = {}),
            model = imagePath,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Box(
            modifier = Modifier
                .padding(20.dp)
                .size(40.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color.Gray.copy(0.5f))
                .clickable(onClick = onBackPressed)
                .align(Alignment.TopStart),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun FullScreenHomeImageView(
    imagePath: String,
    onBackPressed: () -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
                .clickable(onClick = {}),
            model = imagePath,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Box(
            modifier = Modifier
                .padding(20.dp)
                .size(40.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color.Gray.copy(0.5f))
                .clickable(onClick = onBackPressed)
                .align(Alignment.TopStart),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                tint = Color.Gray
            )
        }
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
//                        val destPath =
//                            Environment.getExternalStoragePublicDirectory(
//                                Environment.DIRECTORY_PICTURES
//                            ).absolutePath +
//                                    File.separator +
//                                    context.getString(R.string.app_name)
//                        copyFile(
//                            inputFile = imagePath,
//                            outputPath = destPath
//                        )
//                        Toast
//                            .makeText(
//                                context,
//                                "Downloaded at $destPath",
//                                Toast.LENGTH_SHORT
//                            )
//                            .show()
                        context.shareFile(File(imagePath))
                    }),
                painter = painterResource(id = R.drawable.ic_share),
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