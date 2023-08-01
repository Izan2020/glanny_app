package com.glantrox.glanny

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.glantrox.glanny.core.viewmodel.AuthViewModel
import com.glantrox.glanny.routes.AppNavigator
import com.glantrox.glanny.ui.theme.GlannyTheme

class MainActivity : ComponentActivity() {
    private lateinit var _authViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        setContent {
            GlannyTheme {
              AppNavigator().NavigationDelegate(
                  authViewModel = _authViewModel
              )
            }
        }
    }
}

