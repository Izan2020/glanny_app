package com.glantrox.glanny.`interface`.pages

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.glantrox.glanny.`interface`.widgets.ItemCalls

@Composable
fun CallsPage() {
    return LazyColumn() {
        items(12) {
            ItemCalls()
        }
    }
}