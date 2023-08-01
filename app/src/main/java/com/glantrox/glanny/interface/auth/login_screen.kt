package com.glantrox.glanny.`interface`.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.glantrox.glanny.R
import com.glantrox.glanny.core.viewmodel.AuthViewModel
import com.glantrox.glanny.`interface`.widgets.FormTextField
import com.glantrox.glanny.`interface`.widgets.PrimaryAppButton
import com.glantrox.glanny.`interface`.widgets.SecondaryAppButton
import com.glantrox.glanny.routes.AppNavigator
import com.glantrox.glanny.routes.Pages
import com.glantrox.glanny.theme.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LoginScreen(
    navController: NavHostController = rememberNavController(),
) {

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.padding(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FormTextField(
                    value = "",
                    onValueChange = {},
                    placeholderHint = "Email",
                    leadingIcon = R.drawable.baseline_email_24
                )
                Spacer(modifier = Modifier.height(16.dp))
                FormTextField(
                    value = "",
                    onValueChange = {},
                    placeholderHint = "Password",
                    leadingIcon = R.drawable.baseline_password_24
                )
                Spacer(modifier = Modifier.height(16.dp))
                PrimaryAppButton(
                    text = "Login",
                    onTap = {}
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("Dont have Account? ")
                Spacer(modifier = Modifier.height(16.dp))
                SecondaryAppButton(
                    text = "Register",
                    onTap = {
                        AppNavigator().pushAndReplace(navController, Pages.registerScreen.route)
                    }
                )
            }
        }
    }
}