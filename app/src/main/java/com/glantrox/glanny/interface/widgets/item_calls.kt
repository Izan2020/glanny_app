package com.glantrox.glanny.`interface`.widgets

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.R.drawable.ic_call_answer
import coil.compose.AsyncImage
import com.glantrox.glanny.R.drawable.baseline_arrow_outward_24
import com.glantrox.glanny.theme.AppColors

@SuppressLint("PrivateResource")
@Preview
@Composable
fun ItemCalls(onTap: () -> Unit = {}) {
    return Column {
        Box(
            modifier = Modifier.clickable { onTap() }
        ) {
            Row(
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .background(AppColors().white)
                    .padding(11.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp)
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(AppColors().gray),
                            model = "",
                            contentDescription = "profilePicture"
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column() {
                        Text(
                            text = "User Name",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                painter = painterResource(baseline_arrow_outward_24),
                                contentDescription = "",
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "December 6, 12:36",
                                style = TextStyle(
                                    color = AppColors().gray
                                )
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Icon(
                        modifier = Modifier.size(23.dp),
                        painter = painterResource(ic_call_answer),
                        contentDescription = ""
                    )
                }
            }
            Divider(thickness = 0.6.dp)
        }
    }
}