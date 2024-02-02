package com.mobileappxperts.mockupgenerator.mockupmaker.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobileappxperts.mockupgenerator.mockupmaker.R
import com.mobileappxperts.mockupgenerator.mockupmaker.core.AppOpenAdManager
import com.mobileappxperts.mockupgenerator.mockupmaker.core.utils.feedBackApp
import com.mobileappxperts.mockupgenerator.mockupmaker.core.utils.privacyPolicy
import com.mobileappxperts.mockupgenerator.mockupmaker.core.utils.ratingApp
import com.mobileappxperts.mockupgenerator.mockupmaker.core.utils.shareApp
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.AppFonts
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.BgColor

@Composable
fun SettingScreen(onBackPressed: () -> Unit) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgColor),
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
                text = "Settings",
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
        SettingRow(painterResource(id = R.drawable.ic_privacy_policy), "Privacy Policy") {
            AppOpenAdManager.isShowingAd = true
            context.privacyPolicy()
        }
        SettingRow(painterResource(id = R.drawable.ic_share_app), "Share App") {
            AppOpenAdManager.isShowingAd = true
            context.shareApp()
        }
        SettingRow(painterResource(id = R.drawable.ic_rate_us), "Rating & Review") {
            AppOpenAdManager.isShowingAd = true
            context.ratingApp()
        }
        SettingRow(painterResource(id = R.drawable.ic_feedback), "Feedback") {
            AppOpenAdManager.isShowingAd = true
            context.feedBackApp()
        }
    }
}

@Composable
private fun SettingRow(
    painter: Painter,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(start = 14.dp)
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(26.dp), painter = painter, contentDescription = null
        )
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = text,
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontFamily = AppFonts,
                fontSize = 18.sp
            )
        )
    }
}