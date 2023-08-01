package com.glantrox.glanny.`interface`

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.glantrox.glanny.R
import com.glantrox.glanny.R.drawable.baseline_camera_alt_24
import com.glantrox.glanny.R.drawable.baseline_edit_24
import com.glantrox.glanny.R.drawable.baseline_person_24
import com.glantrox.glanny.theme.AppColors
import org.jetbrains.annotations.Async

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSettingScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Profile",
                        style = TextStyle(
                            color = AppColors().white,
                                    fontSize = 19.sp,
                        )
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable {  },
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        contentDescription = "",
                        tint = AppColors().white
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(AppColors().primaryColor)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(22.dp))
            Box {
                Box(
                    modifier = Modifier
                        .width(130.dp)
                        .height(130.dp)
                        .clip(CircleShape)
                        .background(AppColors().gray)
                ) {
                    AsyncImage(model = "", contentDescription = "")

                }
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                        .clip(CircleShape)
                        .background(AppColors().secondaryColorz)
                        .align(Alignment.BottomEnd)
                ) {
                    Icon(
                        painter = painterResource(baseline_camera_alt_24),
                        contentDescription = "",
                        modifier = Modifier.align(Alignment.Center),
                        tint = AppColors().white,
                    )
                }
            }
            Spacer(modifier = Modifier.height(22.dp))
            ItemProfileSetting(
                title = "Name",
                subTitle = "Hamas",
                description = "This is not your username or pin. This name will be visible to your WhatsApp contacts.",
                icon = R.drawable.baseline_person_24,
                onTapEdit = {}
            )
        }
    }
}

@Composable
fun ItemProfileSetting(
    title: String,
    subTitle: String,
    description: String?,
    icon: Int,
    onTapEdit: () -> Unit,
) {
    return Row(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Column(
            modifier = Modifier
                .weight(2f)

            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "",
                tint = AppColors().primaryColor

            )
        }
        Column(
            modifier = Modifier
                .weight(8f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Name",
                        style = TextStyle(
                            color = AppColors().darkGray
                        )
                    )
                    Text("Hamas",
                        style = TextStyle(
                            fontSize = 16.sp
                        )
                    )
                }
                Icon(
                    painter = painterResource(baseline_edit_24),
                    contentDescription = "",
                    tint = AppColors().primaryColor,
                    modifier = Modifier.padding(end = 14.dp)
                )
            }
            Text("This is not your username or pin, this name will be visible to your Whatsapp",
                style = TextStyle(
                    color = AppColors().gray,
                    fontSize = 13.sp
                ),

                )
            Spacer(modifier = Modifier.height(12.dp))
            Divider()
        }
    }
}