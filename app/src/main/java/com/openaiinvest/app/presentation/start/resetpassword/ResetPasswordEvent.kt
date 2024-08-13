package com.openaiinvest.app.presentation.start.resetpassword

import android.content.Context
import androidx.navigation.NavHostController
import com.openaiinvest.app.presentation.start.signin.SignInEvent

sealed class  ResetPasswordEvent {
    data class SendRecoveryMail(val navController: NavHostController, val context: Context) :
        ResetPasswordEvent()
    data class EnteredEmail(val value: String) : ResetPasswordEvent()

}