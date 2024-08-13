package com.openaiinvest.app.presentation.start.signin

import android.content.Context
import androidx.navigation.NavHostController
import com.openaiinvest.app.presentation.start.signup.SignUpEvent

sealed class SignInEvent {
    data class PerformLogin(val navController: NavHostController, val context: Context) :
        SignInEvent()
    data class EnteredEmail(val value: String) : SignInEvent()
    data class EnteredPassword(val value: String) : SignInEvent()
}