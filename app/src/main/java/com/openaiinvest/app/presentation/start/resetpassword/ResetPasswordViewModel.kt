package com.openaiinvest.app.presentation.start.resetpassword

import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.openaiinvest.app.domain.repository.AuthRepository
import com.openaiinvest.app.presentation.common.OpenAIInvestTextFieldState
import com.openaiinvest.app.presentation.util.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _email = mutableStateOf(
        OpenAIInvestTextFieldState(
            hint = "Email Address"
        )
    )
    val email: State<OpenAIInvestTextFieldState> = _email

    fun onEvent(
        event: ResetPasswordEvent
    ) {
        when (event) {
            is ResetPasswordEvent.SendRecoveryMail -> {
                viewModelScope.launch {
                    if (_email.value.text == "") {
                        Toast.makeText(
                            event.context,
                            "Please enter a valid email and password",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        try {
                            authRepository.resetPassword(_email.value.text)
                            Toast.makeText(
                                event.context,
                                "Recovery mail sent! Check your email",
                                Toast.LENGTH_SHORT
                            ).show()
                            event.navController.navigate(Screen.OnboardingScreen.route) {
                                popUpTo(event.navController.graph.findStartDestination().id) {
                                    inclusive = true
                                }
                            }
                        } catch (e: Exception) {
                            Toast.makeText(
                                event.context,
                                "Unknown error occurred, please try after sometime",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

            is ResetPasswordEvent.EnteredEmail -> {
                _email.value = email.value.copy(
                    text = event.value
                )
            }
        }

    }
}