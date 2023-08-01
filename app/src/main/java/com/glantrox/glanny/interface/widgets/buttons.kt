package com.glantrox.glanny.`interface`.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card

import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.glantrox.glanny.theme.AppColors


@Composable
fun PrimaryAppButton( text: String, onTap: () -> Unit) {
    return Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable { onTap() }
        ,
        colors = CardDefaults.cardColors(AppColors().primaryColor)
    ) {
       Column(
           modifier = Modifier.fillMaxSize(),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           Text(text = text,
               style = TextStyle(
                   color = AppColors().white
               )
           )
       }
    }
}

@Composable
fun SecondaryAppButton(text: String, onTap: () -> Unit) {
    return Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable { onTap() }
        ,
        colors = CardDefaults.cardColors(AppColors().white),
        border = CardDefaults.outlinedCardBorder(true)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = text,
                style = TextStyle(
                    color = AppColors().primaryColor
                )
            )
        }
    }
}