package mockupmaker.screenshots.mockup.generator.core.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExpandableFAB(
    modifier: Modifier = Modifier,
    onFabItemClicked: (fabItem: ExpandableFABMenuItem) -> Unit,
    menuItems: List<ExpandableFABMenuItem>
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 45f else 0f
    )

    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = expandedState,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut()
        ) {
            LazyColumn(
                modifier = Modifier.wrapContentSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(menuItems.size) { index ->
                    ExpandableFABItem(
                        item = menuItems[index],
                        onFabItemClicked = onFabItemClicked
                    )
                }

                item {}
            }
        }

        FloatingActionButton(
            onClick = { expandedState = !expandedState },
            backgroundColor = Blue
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = "FAB",
                modifier = Modifier.rotate(rotationState),
            )
        }
    }
}

@Composable
fun ExpandableFABItem(
    item: ExpandableFABMenuItem,
    onFabItemClicked: (item: ExpandableFABMenuItem) -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentSize()
            .padding(end = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .background(MaterialTheme.colors.surface)
                .padding(horizontal = 6.dp, vertical = 4.dp)
        )

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(42.dp)
                .clickable { onFabItemClicked(item) }
                .background(Blue),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = "Create New Note",
                tint = White
            )
        }
    }
}

data class ExpandableFABMenuItem(
    val label: String,
    val icon: ImageVector
)