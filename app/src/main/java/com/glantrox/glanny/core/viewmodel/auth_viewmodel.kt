package com.glantrox.glanny.core.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glantrox.glanny.core.constants.ApiState
import com.glantrox.glanny.core.models.RegistrationResponse
import com.glantrox.glanny.core.models.User
import com.glantrox.glanny.core.services.AuthRepository
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.launch
import java.lang.Exception


class AuthViewModel: ViewModel() {
    val authRepository = AuthRepository()

    // Register State
    private val _registerState = mutableStateOf(ApiState.none)
    val registerState: State<ApiState> = _registerState

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

    // O=========================================================================>
    // ? Register User
    // <=========================================================================O

        fun registerUser(user: User, callBack: (Boolean) -> Unit) {
         viewModelScope.launch {
             try {
                 _setRegisterState(ApiState.loading)
                 val response = authRepository.registerUserWithEmailAndPassword(user)
                 if(response == RegistrationResponse.Success) {
                     _setRegisterState(ApiState.success)
                     callBack(true)
                 } else {
                     _setRegisterState(ApiState.error)
                     callBack(false)
                 }

             } catch (e: FirebaseAuthException) {
                 _setAuthMessages(e.message?: "Unknown Error Occured")
                 _setRegisterState(ApiState.error)
                 callBack(false)
             }
         }

    }
}