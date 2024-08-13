package com.openaiinvest.app.presentation.start.signup

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
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _email = mutableStateOf(
        OpenAIInvestTextFieldState(
            hint = "Email Address"
        )
    )
    val email: State<OpenAIInvestTextFieldState> = _email

    private val _password = mutableStateOf(
        OpenAIInvestTextFieldState(
            hint = "Password"
        )
    )
    val password: State<OpenAIInvestTextFieldState> = _password

    private val _confirmPassword = mutableStateOf(
        OpenAIInvestTextFieldState(
            hint = "Confirm Password"
        )
    )
    val confirmPassword: State<OpenAIInvestTextFieldState> = _confirmPassword

    private val _referral = mutableStateOf(
        OpenAIInvestTextFieldState(
            hint = "Referral Code (If Any)"
        )
    )
    val referral: State<OpenAIInvestTextFieldState> = _referral

    fun onEvent(
        event: SignUpEvent
    ) {
        when (event) {
            is SignUpEvent.PerformSignUp -> {
                viewModelScope.launch {
                    if (_email.value.text == "" || _password.value.text == "" || _confirmPassword.value.text == "") {
                        Toast.makeText(
                            event.context,
                            "Please enter a valid email and password",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        if (
                            _password.value.text !=  _confirmPassword.value.text
                        ){
                            Toast.makeText(
                                event.context,
                                "Please check if you entered the same password in confirm password",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        try {
                            authRepository.register(_email.value.text, _password.value.text)
                            Toast.makeText(
                                event.context,
                                "A verification email is sent to your email address.",
                                Toast.LENGTH_SHORT
                            ).show()
                            event.navController.navigate(Screen.HomeScreen.route) {
                                popUpTo(event.navController.graph.findStartDestination().id) {
                                    inclusive = true
                                }
                            }
                        } catch (e: Exception){
                            Toast.makeText(
                                event.context,
                                "Unknown error occurred, please try after sometime",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

            is SignUpEvent.EnteredEmail -> {
                _email.value = email.value.copy(
                    text = event.value
                )
            }

            is SignUpEvent.EnteredPassword -> {
                _password.value = password.value.copy(
                    text = event.value
                )
            }

            is SignUpEvent.EnteredConfirmPassword -> {
                _confirmPassword.value = confirmPassword.value.copy(
                    text = event.value
                )
            }

            is SignUpEvent.EnteredReferral -> {
                _referral.value = referral.value.copy(
                    text = event.value
                )
            }
        }
    }

}