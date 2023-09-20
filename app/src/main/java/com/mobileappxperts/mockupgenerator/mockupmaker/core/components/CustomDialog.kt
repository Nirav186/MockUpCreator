package com.mobileappxperts.mockupgenerator.mockupmaker.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.AppColor
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.AppFonts
import com.mobileappxperts.mockupgenerator.mockupmaker.ui.theme.SecondaryColor

@Composable
fun CustomDialog(text: String, setShowDialog: (Boolean) -> Unit) {

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Configs",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Icon(
                            imageVector = Icons.Filled.Cancel,
                            contentDescription = "",
                            tint = colorResource(android.R.color.darker_gray),
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable { setShowDialog(false) }
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                BorderStroke(
                                    width = 2.dp,
                                    color = colorResource(id = android.R.color.holo_green_light)
                                ),
                                shape = RoundedCornerShape(10)
                            )
                            .padding(10.dp),
                        text = text
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.padding(horizontal = 40.dp)
                    ) {
                        Button(
                            onClick = {
                                setShowDialog(false)
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                        ) {
                            Text(text = "Copy")
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewCustomDialog() {
    DeleteConfirmationDialog({}) {}
}

@Composable
fun DeleteConfirmationDialog(setShowDialog: (Boolean) -> Unit, onDeleteClick: () -> Unit) {

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Are you sure you want to delete?",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = SecondaryColor,
                        textAlign = TextAlign.Center
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .border(
                                border = BorderStroke(width = 2.dp, color = AppColor),
                                shape = RoundedCornerShape(30.dp)
                            )
                            .clickable(onClick = { setShowDialog(false) })
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Cancel",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = AppFonts,
                                fontWeight = FontWeight.SemiBold,
                                color = SecondaryColor
                            )
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .border(
                                border = BorderStroke(width = 2.dp, color = AppColor),
                                shape = RoundedCornerShape(30.dp)
                            )
                            .clip(RoundedCornerShape(30.dp))
                            .background(AppColor)
                            .clickable(onClick = onDeleteClick)
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Delete",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = AppFonts,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        )
                    }
                }
            }
        }
    }
}