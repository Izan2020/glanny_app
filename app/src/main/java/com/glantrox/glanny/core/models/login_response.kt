package com.glantrox.glanny.core.models

sealed class LoginResponse {
    object Success : LoginResponse()
    data class Error(val message: String) : LoginResponse()
}