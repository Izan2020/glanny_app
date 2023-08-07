package com.glantrox.glanny.`interface`.auth

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.glantrox.glanny.R
import com.glantrox.glanny.core.constants.ApiState
import com.glantrox.glanny.core.constants.AuthState.*
import com.glantrox.glanny.core.models.User
import com.glantrox.glanny.core.viewmodel.AuthViewModel
import com.glantrox.glanny.`interface`.widgets.FormTextField
import com.glantrox.glanny.`interface`.widgets.PrimaryAppButton
import com.glantrox.glanny.`interface`.widgets.SecondaryAppButton
import com.glantrox.glanny.routes.AppNavigator
import com.glantrox.glanny.routes.Pages
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RegisterContent(
    onTapGotoLogin: () -> Unit,
    onTapRegister: (username: String, email: String, password: String) -> Unit,
    serviceState: ApiState
) {
    var textUsername by remember { mutableStateOf("") }
    var textEmail by remember { mutableStateOf("") }
    var textPassword by remember { mutableStateOf("") }
    var textConfirmPassword by remember { mutableStateOf("") }
    return Column(
        modifier = Modifier.padding(22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormTextField(
            value = textUsername,
            onValueChange = { textUsername = it },
            placeholderHint = "Name",
            leadingIcon = R.drawable.baseline_drive_file_rename_outline_24
        )
        Spacer(modifier = Modifier.height(16.dp))
        FormTextField(
            value = textEmail,
            onValueChange = { textEmail = it },
            placeholderHint = "Email",
            leadingIcon = R.drawable.baseline_email_24
        )
        Spacer(modifier = Modifier.height(16.dp))
        FormTextField(
            value = textPassword,
            onValueChange = { textPassword = it },
            placeholderHint = "Password",
            leadingIcon = R.drawable.baseline_password_24
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (textPassword != "") {
            FormTextField(
                value = textConfirmPassword,
                onValueChange = { textConfirmPassword = it },
                placeholderHint = "Confirm Password",
                leadingIcon = null
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        AnimatedVisibility(visible = serviceState != ApiState.loading) {
            PrimaryAppButton(
                text = "Register",
                onTap = { onTapRegister(
                    textUsername,
                    textEmail,
                    textPassword
                ) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Have account Already? ")
        Spacer(modifier = Modifier.height(16.dp))
        SecondaryAppButton(
            text = "Login",
            onTap = {
                onTapGotoLogin()
            }
        )
    }
}

@Composable
fun LoginContent(
    onTapGotoRegister: () -> Unit,
    onTapLogin: (email: String, password: String) -> Unit,
    serviceState: ApiState
) {
    var emailText by remember { mutableStateOf("")}
    var passwordText by remember { mutableStateOf("")}


    return Column(
        modifier = Modifier.padding(22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormTextField(
            value = emailText,
            onValueChange = {
                            emailText = it
            },
            placeholderHint = "Email",
            leadingIcon = R.drawable.baseline_email_24
        )
        Spacer(modifier = Modifier.height(16.dp))
        FormTextField(
            value = passwordText,
            onValueChange = { passwordText = it },
            placeholderHint = "Password",
            leadingIcon = R.drawable.baseline_password_24
        )
        Spacer(modifier = Modifier.height(16.dp))
        AnimatedVisibility(visible = serviceState != ApiState.loading) {
            PrimaryAppButton(
                text = "Login",
                onTap = {
                    onTapLogin(
                        emailText,
                        passwordText
                    )
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Dont have Account? ")
        Spacer(modifier = Modifier.height(16.dp))
        SecondaryAppButton(
            text = "Register",
            onTap = {
                onTapGotoRegister()
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun AuthScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: AuthViewModel = AuthViewModel(),
) {
    var authState = viewModel.authState.value
    var context = LocalContext.current


    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (authState) {
                login -> LoginContent(
                    serviceState = viewModel.loginState.value,
                    onTapGotoRegister = {viewModel.setAuthState(register)},
                    onTapLogin = { email, password ->
                        var userCredentials = User("",email,password)
                        viewModel.executeAuthentication(userCredentials){ isSuccess ->
                            if (isSuccess) {
                                AppNavigator().pushAndReplace(navController, Pages.homeScreen.route)
                            } else {
                                Toast.makeText(context, viewModel.authMessages.value, Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    )

                register -> {
                    RegisterContent(
                        serviceState = viewModel.registerState.value,
                        onTapGotoLogin = {viewModel.setAuthState(login)},
                        onTapRegister = {username, email, password ->
                            var userCredentials = User(username,email,password)
                            viewModel.executeAuthentication(userCredentials) {isSuccess ->
                                if(isSuccess) {
                                    AppNavigator().pushAndReplace(navController, Pages.homeScreen.route)
                                } else {
                                    Toast.makeText(context, viewModel.authMessages.value, Toast.LENGTH_SHORT).show()
                                }
                            }

                        }
                    )
                }

                authenticating -> {
                    Image(
                        painter = painterResource(R.drawable.logo_glanny),
                        contentDescription = "logoGlanny" )
                }

                success -> AppNavigator().pushAndReplace(navController, Pages.homeScreen.route)
            }
        }
    }
}



