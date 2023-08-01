package com.glantrox.glanny.`interface`.widgets

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.glantrox.glanny.theme.AppColors

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun FormTextField(
    value: String = "Hint",
    onValueChange: (String) -> Unit = {},
    placeholderHint: String = "",
    leadingIcon: Int? = null,
    trailingIcon: Unit? = null
) {
    return Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxSize(),
            placeholder = {
                Text(text = placeholderHint,
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = AppColors().gray
                    )
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = AppColors().white,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            leadingIcon = {
                          if(leadingIcon != null) {
                              Icon(
                                  painter = painterResource(leadingIcon),
                                  contentDescription = ""
                              )
                          }
            },
            trailingIcon = {trailingIcon}
        )

    }
}