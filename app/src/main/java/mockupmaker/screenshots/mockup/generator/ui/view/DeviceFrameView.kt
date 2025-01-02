package mockupmaker.screenshots.mockup.generator.ui.view

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import mockupmaker.screenshots.mockup.generator.data.model.DeviceFrameItem

class DeviceFrameView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    val frame: MutableState<DeviceFrameItem>,
    val bitmap: MutableState<Bitmap>?,
    val onLoading: (Boolean) -> Unit,
) : AbstractComposeView(context, attributeSet, defStyleAttr) {

    @Composable
    override fun Content() {
        DeviceFrame(frame = frame.value)
    }

    @Composable
    fun DeviceFrame(frame: DeviceFrameItem) {
        Box(
            modifier = Modifier
                .width((frame.width).dp)
                .height((frame.height).dp)
        ) {
            bitmap?.value?.let {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(frame.getPadding()),
                    bitmap = it.asImageBitmap(),
                    contentDescription = "ScreenShot",
                    contentScale = ContentScale.FillBounds
                )
            }
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = frame.frameUrl)
                    .allowHardware(false)
                    .build()
            )
            val painterState = painter.state
            if (painterState is AsyncImagePainter.State.Loading) {
                onLoading(true)
            } else {
                onLoading(false)
            }
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}