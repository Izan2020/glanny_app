package com.glantrox.glanny.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.glantrox.glanny.core.services.AuthRepository
import com.glantrox.glanny.core.viewmodel.AuthViewModel
import com.glantrox.glanny.`interface`.ChatScreen
import com.glantrox.glanny.`interface`.HomeScreen
import com.glantrox.glanny.`interface`.auth.AuthScreen


import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

sealed class Pages(val route: String) {
    // Auth Pages
    object splashScreen: Pages("splash_screen")
    object authScreen: Pages("auth_screen")


    // Logged in Pages
    object  homeScreen: Pages("home_screen")
    object  chatScreen: Pages("chat_screen")
}

class AppNavigator{
    @Composable
    fun NavigationDelegate(
        authViewModel: AuthViewModel
    ) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Pages.authScreen.route ) {
            composable(Pages.homeScreen.route) {
                HomeScreen(navController = navController)
            }
            composable(Pages.chatScreen.route) {
                ChatScreen(navController = navController)
            }
            composable(Pages.authScreen.route) {
                AuthScreen(
                    navController = navController,
                    viewModel = authViewModel,
                )
            }


        }
    }

    fun _init(): Boolean {
        var authRepo = AuthRepository()
        return false
    }

    fun push(navController: NavHostController, screen: String) {
        navController.navigate(screen)
    }

    fun pushAndReplace(navController: NavHostController ,screen: String) {
        navController.navigate(screen) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

    fun pop(navController: NavHostController) {
        navController.popBackStack()
    }
}