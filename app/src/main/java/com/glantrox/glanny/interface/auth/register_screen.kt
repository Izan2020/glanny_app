package com.glantrox.glanny.`interface`.auth

import android.widget.Toast
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.glantrox.glanny.R
import com.glantrox.glanny.core.constants.ApiState
import com.glantrox.glanny.core.models.User
import com.glantrox.glanny.core.viewmodel.AuthViewModel
import com.glantrox.glanny.`interface`.widgets.FormTextField
import com.glantrox.glanny.`interface`.widgets.PrimaryAppButton
import com.glantrox.glanny.`interface`.widgets.SecondaryAppButton
import com.glantrox.glanny.routes.AppNavigator
import com.glantrox.glanny.routes.Pages
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RegisterScreen(
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel = AuthViewModel(),
    scope: CoroutineScope = rememberCoroutineScope()
) {
    var textName by remember { mutableStateOf("")}
    var textEmail by remember { mutableStateOf("")}
    var textPassword by remember { mutableStateOf("")}
    var textConfirmPassword by remember { mutableStateOf("")}
    val context = LocalContext.current

    fun _registerMethod() {
        // Validate Empty Fields
        if (
            textName == "" ||
            textEmail == "" ||
            textPassword == "" ||
            textConfirmPassword == ""
        ) {
            Toast.makeText(context, "Fill the Blanks", Toast.LENGTH_SHORT).show()
            return
        }
        // Validate Confirm Password
        if(textConfirmPassword != textPassword) {
            Toast.makeText(context, "Confirm your Password", Toast.LENGTH_SHORT).show()
            return
        }

        // User Credential
        val userCredential: User = User(
            textName,
            textEmail,
            textPassword
        )

        // FirebaseAuth in ViewModel
        authViewModel.registerUser(userCredential) {isSuccess ->
            if(isSuccess) {
                AppNavigator().pushAndReplace(navController , Pages.loginScreen.route)
            } else {
                Toast.makeText(context, authViewModel.authMessages.value, Toast.LENGTH_SHORT).show()
            }
        }
    }
    
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
                    value = textName,
                    onValueChange = {textName = it},
                    placeholderHint = "Name",
                    leadingIcon = R.drawable.baseline_drive_file_rename_outline_24
                )
                Spacer(modifier = Modifier.height(16.dp))
                FormTextField(
                    value = textEmail,
                    onValueChange = {textEmail = it},
                    placeholderHint = "Email",
                    leadingIcon = R.drawable.baseline_email_24
                )
                Spacer(modifier = Modifier.height(16.dp))
                FormTextField(
                    value = textPassword,
                    onValueChange = {textPassword = it},
                    placeholderHint = "Password",
                    leadingIcon = R.drawable.baseline_password_24
                )
                Spacer(modifier = Modifier.height(16.dp))
                if(textPassword != "") {
                    FormTextField(
                        value = textConfirmPassword,
                        onValueChange = { textConfirmPassword = it },
                        placeholderHint = "Confirm Password",
                        leadingIcon = null
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                PrimaryAppButton(
                    text = when(authViewModel.registerState.value) {
                        ApiState.loading -> "Loading..."
                        ApiState.error -> "Try Again"
                        else -> "Register"
                    },
                    onTap = { _registerMethod() }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("Have account Already? ")
                Spacer(modifier = Modifier.height(16.dp))
                SecondaryAppButton(
                    text = "Login",
                    onTap = {
                      AppNavigator().pushAndReplace(navController, Pages.loginScreen.route)
                    }
                )
            }
        }
    }
}