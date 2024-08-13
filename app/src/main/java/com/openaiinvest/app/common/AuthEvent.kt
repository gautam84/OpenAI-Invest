package com.openaiinvest.app.common

sealed class AuthEvent {
    object Success : AuthEvent()
    data class Error(val errorMessage: String) : AuthEvent()

    companion object {
        fun success(): AuthEvent = Success
        fun error(errorMessage: String): AuthEvent = Error(errorMessage)
    }
}
