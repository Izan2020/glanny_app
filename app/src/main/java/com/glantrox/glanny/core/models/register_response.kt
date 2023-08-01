package com.glantrox.glanny.core.models

import java.util.Objects

sealed class RegistrationResponse {
    object Success : RegistrationResponse()
    data class Error(val message: String) : RegistrationResponse()
}
