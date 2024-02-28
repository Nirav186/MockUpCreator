package mockupmaker.screenshots.mockup.generator.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mockupmaker.screenshots.mockup.generator.ui.theme.AppColor
import mockupmaker.screenshots.mockup.generator.ui.theme.AppFonts

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppButton(
    modifier: Modifier,
    buttonText: String,
    onClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .height(50.dp),
            elevation = 0.dp,
            onClick = {
                focusManager.clearFocus()
                onClick()
            },
            backgroundColor = AppColor
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = AppColor),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = buttonText, fontSize = 16.sp,
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = AppFonts
                    )
                )
            }
        }
    }
}

@Composable
fun ButtonWithBg(
    modifier: Modifier,
    imageVector: ImageVector,
    color: Color
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color.copy(alpha = 0.15f)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.padding(8.dp),
            imageVector = imageVector,
            contentDescription = null,
            tint = color
        )
    }
}

@Preview
@Composable
fun AppButton() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AppColor,
            contentColor = Color.White,
            disabledBackgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
                .compositeOver(MaterialTheme.colors.surface),
            disabledContentColor = MaterialTheme.colors.onSurface
                .copy(alpha = ContentAlpha.disabled)
        )
    ) {
        Text(
            text = "Yes",
            style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview
@Composable
fun OutLinedButton() {
    OutlinedButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(10.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.primary,
            disabledContentColor = MaterialTheme.colors.onSurface
                .copy(alpha = ContentAlpha.disabled)
        ),
        border = BorderStroke(width = 2.dp, color = AppColor)
    ) {
        Text(
            text = "Yes",
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}