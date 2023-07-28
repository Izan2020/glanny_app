package com.glantrox.glanny.`interface`

import android.annotation.SuppressLint

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width

import androidx.compose.material.TabRowDefaults.tabIndicatorOffset

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material.TabRow
import androidx.compose.material3.TabRowDefaults

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.glantrox.glanny.R
import com.glantrox.glanny.R.drawable.baseline_camera_alt_24
import com.glantrox.glanny.R.drawable.baseline_more_vert_24
import com.glantrox.glanny.`interface`.pages.CallsPage
import com.glantrox.glanny.`interface`.pages.ChatPage

import com.glantrox.glanny.`interface`.pages.StatusPage
import com.glantrox.glanny.theme.AppColors



@SuppressLint("UnrememberedMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = false)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val tabs = listOf("","CHAT", "STATUS", "CALLS")
    var selectedTabIndex by remember { mutableStateOf(1) }

    return Scaffold(

        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = AppColors().primaryColor),
                title = { Text(
                    "glanny.",
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                            )
                    )
                },
                actions = {
                    IconButton(
                        onClick = { /*TODO*/ },
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_search_24),
                                contentDescription = "Search",
                                tint = Color.White
                                )
                        }
                    )
                    IconButton(
                        onClick = { /*TODO*/ },
                        content = {
                            Icon(
                                painter = painterResource(id = baseline_more_vert_24),
                                contentDescription = "More",
                                tint = Color.White
                            )
                        }
                    )
                }
            ) }
    ) {
       Column(modifier = Modifier.padding(it)) {
           TabRow(
               selectedTabIndex = selectedTabIndex,
               backgroundColor = AppColors().primaryColor,
               contentColor = Color.Black,
               indicator = { tabPositions ->
                   TabRowDefaults.Indicator(
                       modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                       height = 2.dp,
                       color = AppColors().white
                   )
               }
           ) {
               tabs.forEachIndexed { index, title ->
                   Tab(
                       text = {
                              if (index == 0) {
                                  Icon(
                                      painter = painterResource(baseline_camera_alt_24),
                                      contentDescription = "",
                                      tint = AppColors().white
                                  )
                              }
                           Text(title, color = AppColors().white)
                       },
                       selected = selectedTabIndex == index,
                       onClick = { selectedTabIndex = index },
                   )
               }
           }
           when(selectedTabIndex) {
               0 -> Text(text = "Camera")
               1 -> ChatPage()
               2 -> StatusPage()
               3 -> CallsPage()

           }

       }
       }
    }







