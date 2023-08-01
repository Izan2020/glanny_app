package com.glantrox.glanny.`interface`.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.glantrox.glanny.theme.AppColors

@Preview
@Composable
fun ItemStatus(onTap: () -> Unit = {}) {
    return Box(
        modifier = Modifier.clickable { onTap() }
    ) {
        Column {
            Row(
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .background(AppColors().white)
                    .padding(11.dp),
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
                Column {
                    Text(
                        text = "User Name",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "12 hours ago",
                        style = TextStyle(
                            color = AppColors().gray
                        )
                    )
                }
            }
            Divider(thickness = 0.6.dp)
        }
    }
}