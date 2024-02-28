package mockupmaker.screenshots.mockup.generator.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import mockupmaker.screenshots.mockup.generator.R
import mockupmaker.screenshots.mockup.generator.core.ext.getHomeScreenshots
import mockupmaker.screenshots.mockup.generator.data.model.HomeFrame
import mockupmaker.screenshots.mockup.generator.ui.deviceframe.DeviceFrameViewModel
import mockupmaker.screenshots.mockup.generator.ui.theme.AppColor
import mockupmaker.screenshots.mockup.generator.ui.theme.AppFonts
import mockupmaker.screenshots.mockup.generator.ui.theme.FredokaOne
import com.nirav.commons.ads.compose.AdmobBanner

@Composable
fun HomeTabContent(
    onHomeFrameClick: (HomeFrame) -> Unit,
    onImagePreview: (String) -> Unit,
    onSettingsClick: () -> Unit
) {
    val context = LocalContext.current
    val deviceFrameViewModel = hiltViewModel<DeviceFrameViewModel>()
    val frames = deviceFrameViewModel.state.homeFrames
    val homeScreenshots = context.getHomeScreenshots()
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 10.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "App Mockup",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FredokaOne
                )
            )
            Icon(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable(onClick = onSettingsClick)
                    .size(32.dp)
                    .padding(4.dp),
                painter = painterResource(id = R.drawable.ic_setting),
                contentDescription = null
            )
        }
        if (homeScreenshots.isNullOrEmpty().not()) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "My Screenshots",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = AppFonts
                )
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                content = {
                    items(homeScreenshots!!) {
                        AsyncImage(
                            modifier = Modifier
                                .height(120.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .clickable(onClick = {
                                    onImagePreview(it.absolutePath)
                                }),
                            model = it.absolutePath,
                            contentDescription = null,
                            contentScale = ContentScale.Fit
                        )
                    }
                })
        }
        AdmobBanner(modifier = Modifier.fillMaxWidth().padding(top = 10.dp))
        Text(
            modifier = Modifier.padding(10.dp),
            text = "Templates",
            style = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = AppFonts
            )
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(start = 10.dp, end = 10.dp, bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            content = {
                items(frames) {
                    HomeFrameItem(
                        frame = it,
                        onClick = {
                            onHomeFrameClick(it)
                        }
                    )
                }
            })
    }
}

@Composable
fun HomeFrameItem(frame: HomeFrame, onClick: () -> Unit) {
    SubcomposeAsyncImage(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick),
        model = ImageRequest.Builder(LocalContext.current)
            .data(frame.imageUrl)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        loading = {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(0.68f)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = AppColor
                )
            }
        }
    )
}