package com.example.mockupscreenshots.core.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.mockupscreenshots.R
import com.example.mockupscreenshots.ui.theme.SecondaryColor

@Composable
fun DropDownMenu(
    modifier: Modifier = Modifier,
    items: List<String>,
    selectedItem: String,
    onItemSelect: (String) -> Unit
) {
    var mExpanded by remember { mutableStateOf(false) }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    Column(modifier = modifier.padding(0.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .border(
                    width = 2.dp, color = Color(0xFFD2E0ED),
                    shape = RoundedCornerShape(14.dp)
                )
                .clickable { mExpanded = !mExpanded }
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(

                modifier = Modifier
                    .weight(1f)
                    .onGloballyPositioned { coordinates ->
                        mTextFieldSize = coordinates.size.toSize()
                    },
                text = selectedItem,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            )
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .fillMaxSize()
                    .rotate(if (mExpanded) 180f else 0f),
                painter = painterResource(id = R.drawable.ic_down),
                contentDescription = null,
                tint = SecondaryColor
            )
        }
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            items.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        onItemSelect(label)
                        mExpanded = false
                    }) {
                    Text(text = label)
                }
            }
        }
    }
}