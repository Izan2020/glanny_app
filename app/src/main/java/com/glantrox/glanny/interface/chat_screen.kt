package com.glantrox.glanny.`interface`

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.glantrox.glanny.R
import com.glantrox.glanny.R.drawable.baseline_arrow_back_24
import com.glantrox.glanny.R.drawable.baseline_call_24
import com.glantrox.glanny.R.drawable.baseline_mic_24
import com.glantrox.glanny.R.drawable.baseline_more_vert_24
import com.glantrox.glanny.R.drawable.baseline_videocam_24
import com.glantrox.glanny.`interface`.widgets.ItemOurMessage
import com.glantrox.glanny.`interface`.widgets.ItemTheirMessage
import com.glantrox.glanny.theme.AppColors


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ChatScreen() {

    val warningMessageTop = "Messages are end-to-end encrypted. No one outside of this chat, not even WhatsApp, can read or listen to them. Click to learn more."
    val listOfMessage : List<Message> = listOf(
        Message("Halo Apa Kabar", true, "17:52"),
        Message("Kamu Lagi dimana? udah makan belum", true, "17:52"),
        Message("belum", false, "17:52"),
        Message("makan dong", true, "17:52"),
        )
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(AppColors().primaryColor),
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable {  },
                        painter = painterResource(id = baseline_arrow_back_24),
                        contentDescription = "",
                        tint = AppColors().white
                    )
                },
                actions = {
                    Icon(
                        modifier = Modifier.clickable {  },
                        painter = painterResource(id = baseline_videocam_24),
                        contentDescription = "",
                        tint = AppColors().white
                    )
                    Spacer(modifier = Modifier.width(18.dp))
                    Icon(
                        modifier = Modifier.clickable {  },
                        painter = painterResource(id = baseline_call_24),
                        contentDescription = "",
                        tint = AppColors().white
                    )
                    Spacer(modifier = Modifier.width(18.dp))
                    Icon(
                        modifier = Modifier.clickable {  },
                        painter = painterResource(id = baseline_more_vert_24),
                        contentDescription = "",
                        tint = AppColors().white
                    )
                },
                title = {

                    Row {
                        Box(
                            modifier = Modifier
                                .height(40.dp)
                                .width(40.dp)
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(AppColors().gray),
                                model = "",
                                contentDescription = "profilePicture"
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Hamas",
                                style = TextStyle(
                                    fontSize = 19.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = AppColors().white
                                )
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "online",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    color = AppColors().white
                                )
                            )

                        }
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.bgchatdefault),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
          LazyColumn(
              modifier = Modifier.fillMaxHeight(),
              reverseLayout = true
          ) {
              itemsIndexed(listOfMessage.reversed()) { index, chats ->
                  if(index == 0 ) {
                      Spacer(modifier = Modifier.height(63.dp))
                  }
                  if(chats.ourMessage) {
                      ItemOurMessage(chat = chats)
                  } else {
                      ItemTheirMessage(chat = chats)

                  }
                  if(index == listOfMessage.size - 1) {
                      Row(
                          modifier = Modifier
                              .fillMaxWidth()
                              .padding(12.dp)
                          ,
                          horizontalArrangement = Arrangement.Center
                      ) {
                        Card(
                            modifier = Modifier.height(72.dp)
                        ) {
                            Text( warningMessageTop,
                                modifier = Modifier.padding(12.dp),
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                )
                            )
                        }
                      }
                  }
              }
              item {
                  Spacer(
                      modifier = Modifier
                          .fillMaxWidth()
                          .fillMaxHeight()
                  )
              }
          }
          Box(
              modifier = Modifier
                  .align(Alignment.BottomCenter)
                  .fillMaxWidth()


          ){
            Row(
                modifier = Modifier.padding(5.dp)
            ) {
                Card(
                    modifier = Modifier
                        .heightIn(52.dp, 90.dp)
                        .fillMaxWidth()
                        .weight(10f)
                        .clip(CircleShape),
                    colors = CardDefaults.cardColors(AppColors().white)
                ) {
                    TextField(
                        value = "",
                        onValueChange = {},
                        placeholder = { Text("Send Messages") },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_insert_emoticon_24),
                                    contentDescription = "",
                                    tint = AppColors().gray
                                )
                            }
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_camera_alt_24),
                                    contentDescription = "",
                                    tint = AppColors().gray
                                )
                            }
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = AppColors().white,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                }
                Spacer(modifier = Modifier
                    .width(3.dp)
                    .weight(0.1f))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .width(52.dp)
                        .weight(1.5f)
                        .clip(CircleShape),
                    colors = CardDefaults.cardColors(AppColors().primaryColor),
                ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.clickable {  },
                        painter = painterResource(baseline_mic_24),
                        contentDescription = "microphoneIcon",
                        tint = AppColors().white
                    )
                }
                }
            }
          }
        }
    }
}

class Message(
    val message: String,
    val ourMessage: Boolean,
    val timeStamp: String
)