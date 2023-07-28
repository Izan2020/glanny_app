package com.glantrox.glanny.`interface`.pages

import androidx.compose.foundation.lazy.LazyColumn


import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.glantrox.glanny.`interface`.widgets.ItemStatus

@Preview
@Composable
fun StatusPage() {
     LazyColumn {
     items(12) {
          ItemStatus()
     }
     }
}