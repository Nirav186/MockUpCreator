package com.example.mockupscreenshots.ui.edit

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mockupscreenshots.R
import java.io.File


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditScreenShot() {
    val cacheFile = File(LocalContext.current.cacheDir, "temp.png")
    val bmOptions = BitmapFactory.Options()
    val bitmap = BitmapFactory.decodeFile(cacheFile.absolutePath, bmOptions)
//    bitmap = Bitmap.createScaledBitmap(bitmap!!)
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.dog_paws_green),
            contentDescription = null
        )
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                text = "Mockup Screenshots",
                textAlign = TextAlign.Left,
                style = TextStyle(
                    color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold
                )
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
                text = "Add short description or tagline here",
                maxLines = 2,
                style = TextStyle(
                    color = Color.White, fontSize = 18.sp
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = null
                )
            }
        }
        Card(modifier = Modifier
            .padding(20.dp)
            .align(Alignment.BottomEnd),
            shape = CircleShape,
            elevation = 5.dp,
            onClick = {

            }) {
            Icon(
                modifier = Modifier
                    .size(54.dp)
                    .padding(10.dp),
                painter = painterResource(id = R.drawable.insert_image),
                contentDescription = null
            )
        }
    }
}