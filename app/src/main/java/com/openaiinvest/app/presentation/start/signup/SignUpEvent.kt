package com.openaiinvest.app.presentation.start.signup

import android.content.Context
import androidx.navigation.NavHostController

sealed class SignUpEvent {
    data class PerformSignUp(val navController: NavHostController, val context: Context) :
        SignUpEvent()

    data class EnteredEmail(val value: String) : SignUpEvent()
    data class EnteredPassword(val value: String) : SignUpEvent()
    data class EnteredConfirmPassword(val value: String) : SignUpEvent()
    data class EnteredReferral(val value: String) : SignUpEvent()

}