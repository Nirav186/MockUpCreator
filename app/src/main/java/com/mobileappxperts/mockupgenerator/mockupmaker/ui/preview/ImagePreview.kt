package com.mobileappxperts.mockupgenerator.mockupmaker.ui.preview

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.mobileappxperts.mockupgenerator.mockupmaker.core.components.DeleteConfirmationDialog
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

    var isDeletePopupShowing by remember {
        mutableStateOf(Pair("", false))
    }

    if (isDeletePopupShowing.second) {
        DeleteConfirmationDialog(setShowDialog = {
            isDeletePopupShowing = Pair("", false)
        }) {
            val isDeleted = File(isDeletePopupShowing.first).delete()
            if (isDeleted) {
                onBackPressed()
                Toast
                    .makeText(
                        context,
                        "Deleted successfully",
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
        }
    }

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
        Image(
            modifier = Modifier
                .padding(20.dp)
                .size(40.dp)
                .clickable(onClick = onBackPressed),
            painter = painterResource(id = R.drawable.back_btn),
            contentDescription = null
        )
        Row(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.BottomEnd),
            horizontalArrangement = Arrangement.End,
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .clickable(onClick = {
                        context.shareFile(File(imagePath))
                    }),
                painter = painterResource(id = R.drawable.share_btn),
                contentDescription = null
            )
            Image(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(40.dp)
                    .clickable(onClick = {
                        isDeletePopupShowing = Pair(imagePath, true)
                    }),
                painter = painterResource(id = R.drawable.delete_btn),
                contentDescription = null
            )
        }
    }
}