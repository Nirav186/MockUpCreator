package mockupmaker.screenshots.mockup.generator.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomTabs(modifier: Modifier) {
    var selectedIndex by remember { mutableStateOf(0) }

    val list = listOf("Android", "iOS")

    TabRow(
        modifier = modifier
            .height(60.dp)
            .clip(RoundedCornerShape(20.dp))
            .padding(2.dp),
        selectedTabIndex = selectedIndex,
        backgroundColor =Color.Gray,
        indicator = { tabPositions: List<TabPosition> ->
            Box {}
        },
    ) {
        list.forEachIndexed { index, text ->
            val selected = selectedIndex == index
            Tab(
                modifier = if (selected.not()) Modifier
                    .padding(2.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                else Modifier
                    .padding(2.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xff1E76DA)),
                selected = selected,
                onClick = { selectedIndex = index },
                text = { Text(text = text, color = Color(0xff6FAAEE)) }
            )
        }
    }
}