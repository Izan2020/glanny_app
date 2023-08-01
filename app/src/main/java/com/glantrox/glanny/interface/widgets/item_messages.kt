package com.glantrox.glanny.`interface`.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.glantrox.glanny.`interface`.Message
import com.glantrox.glanny.theme.AppColors


@Composable
fun ItemTheirMessage(chat: Message) {
    return Row(
        modifier = Modifier.fillMaxWidth().clipToBounds(),
        horizontalArrangement = Arrangement.Start
    ) {
        Spacer(modifier = Modifier.width(5.dp))
        Card(
            modifier = Modifier
                .padding(2.dp)
                .widthIn(0.dp, 260.dp)
                .shadow(5.dp)
                ,
            colors = CardDefaults.cardColors(AppColors().white),
            shape = RoundedCornerShape(0.dp, 15.dp, 15.dp, 15.dp)
        ) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    chat.message,
                    modifier = Modifier.widthIn(0.dp, 203.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(chat.timeStamp,
                    style = TextStyle(fontSize = 10.5.sp, color = AppColors().gray),

                    )
            }
        }
    }
}

@Composable
fun ItemOurMessage(chat: Message) {
    return Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Card(
            modifier = Modifier
                .padding(2.dp)
                .widthIn(0.dp, 256.dp)
                .shadow(5.dp)
            ,
            colors = CardDefaults.cardColors(AppColors().secondaryColorz),
            shape = RoundedCornerShape(15.dp, 0.dp, 15.dp, 15.dp)
        ) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    chat.message,
                    modifier = Modifier.widthIn(0.dp, 200.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(chat.timeStamp,
                    style = TextStyle(
                        fontSize = 10.5.sp,
                        color = AppColors().gray),

                    )
            }
        }
        Spacer(modifier = Modifier.width(5.dp))
    }
}

@Composable
@Preview
fun PreviewItems() {
    Column {
        ItemOurMessage(chat = Message("Pabji yuk",true,"10:52"))
        ItemTheirMessage(chat = Message("?",true,"17:52"))
    }
}