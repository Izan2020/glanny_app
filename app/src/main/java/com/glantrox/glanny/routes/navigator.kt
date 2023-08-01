package com.glantrox.glanny.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.glantrox.glanny.core.services.AuthRepository
import com.glantrox.glanny.core.viewmodel.AuthViewModel
import com.glantrox.glanny.`interface`.ChatScreen
import com.glantrox.glanny.`interface`.HomeScreen
import com.glantrox.glanny.`interface`.auth.LoginScreen
import com.glantrox.glanny.`interface`.auth.RegisterScreen
import com.glantrox.glanny.`interface`.auth.SplashScreen

sealed class Pages(val route: String) {
    // Auth Pages
    object splashScreen: Pages("splash_screen")
    object loginScreen: Pages("login_screen")
    object registerScreen: Pages("register_screen")

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
        NavHost(navController = navController, startDestination = Pages.registerScreen.route ) {
            composable(Pages.homeScreen.route) {
                HomeScreen(navController = navController)
            }
            composable(Pages.chatScreen.route) {
                ChatScreen(navController = navController)
            }
            composable(Pages.splashScreen.route) {
                SplashScreen(authViewModel = authViewModel)
            }
            composable(Pages.registerScreen.route) {
                RegisterScreen(authViewModel = authViewModel)
            }
            composable(Pages.loginScreen.route) {
                LoginScreen(navController = navController)
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