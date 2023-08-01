package com.glantrox.glanny.`interface`.pages


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.glantrox.glanny.R.drawable.baseline_chat_24
import com.glantrox.glanny.`interface`.widgets.ItemChats
import com.glantrox.glanny.theme.AppColors


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ChatPage() {

    return Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.clip(CircleShape).shadow(5.dp),
                onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(baseline_chat_24),
                    contentDescription = "",
                    tint = AppColors().white
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(12) {
                ItemChats( onTap = {})
            }
        }
    }
}

