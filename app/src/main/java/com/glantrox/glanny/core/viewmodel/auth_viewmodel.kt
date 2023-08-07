package com.glantrox.glanny.core.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glantrox.glanny.core.constants.ApiState
import com.glantrox.glanny.core.constants.AuthState
import com.glantrox.glanny.core.constants.AuthState.*
import com.glantrox.glanny.core.models.User
import com.glantrox.glanny.core.services.AuthRepository
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



class AuthViewModel: ViewModel() {
    val authRepository = AuthRepository()

    // Form State
    private val _formState = mutableStateOf(User())
    val formState: State<User> = _formState

    // Auth State
    private val _authState = mutableStateOf(authenticating)
    val authState: State<AuthState> = _authState

    // Register State
    private val _registerState = mutableStateOf(ApiState.none)
    val registerState: State<ApiState> = _registerState

    // Login State
    private val _loginState = mutableStateOf(ApiState.none)
    val loginState: State<ApiState> = _loginState

    // Current Message
    private val _authMessages = mutableStateOf("Unknown Error Occured")
    val authMessages: State<String> = _authMessages

    // O=========================================================================>
    // ? Additional Functions
    // <=========================================================================O

    fun _setRegisterState(value: ApiState) {
        _registerState.value = value
    }

    fun _setAuthMessages(value: String) {
        _authMessages.value = value
    }

    fun _setLoginState(value: ApiState) {
        _loginState.value = value
    }

    fun setAuthState(value: AuthState) {
        _authState.value = value
    }

    // O=========================================================================>
    // ? Authentication Initialize
    // <=========================================================================O

    fun init() {
        viewModelScope.launch {
            delay(3500)
            val response = authRepository.isLoggedIn()
            when(response) {
                true -> {
                    setAuthState(AuthState.success)
                }
                false -> {
                    setAuthState(AuthState.login)
                }
            }
        }
    }


    // O=========================================================================>
    // ? Authentication Execute
    // <=========================================================================O

    fun executeAuthentication(user: User, callBack: (Boolean) -> Unit) {
        when(_authState.value) {
            login -> {
                viewModelScope.launch {
                    try {
                        _setLoginState(ApiState.loading)
                        authRepository.signInUserWithEmailAndPassword(user)
                        _setLoginState(ApiState.success)
                        callBack(true)
                    } catch (e: FirebaseAuthException) {
                        _setLoginState(ApiState.error)
                        _setAuthMessages(e.message?: "Unknown Error Occured")
                        callBack(false)
                    }
                }
            }
            register -> {
                viewModelScope.launch {
                    try {
                        _setRegisterState(ApiState.loading)
                        authRepository.registerUserWithEmailAndPassword(user)
                        _setRegisterState(ApiState.success)
                        callBack(true)

                    } catch (e: FirebaseAuthException) {
                        _setAuthMessages(e.message?: "Unknown Error Occured")
                        _setRegisterState(ApiState.error)
                        callBack(false)
                    }
                }
            }
            else -> {}
        }
    }

}