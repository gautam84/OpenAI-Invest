package com.openaiinvest.app.presentation.start.signin

import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.openaiinvest.app.domain.repository.AuthRepository
import com.openaiinvest.app.presentation.common.OpenAIInvestTextFieldState
import com.openaiinvest.app.presentation.start.signup.SignUpEvent
import com.openaiinvest.app.presentation.util.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
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


    fun onEvent(
        event: SignInEvent
    ) {
        when (event) {
            is SignInEvent.EnteredEmail -> {
                _email.value = email.value.copy(
                    text = event.value
                )
            }

            is SignInEvent.EnteredPassword -> {
                _password.value = password.value.copy(
                    text = event.value
                )
            }

            is SignInEvent.PerformLogin -> {
                viewModelScope.launch {
                    if (_email.value.text == "" || _password.value.text == "") {
                        Toast.makeText(
                            event.context,
                            "Please enter a valid email and password",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        try {
                            authRepository.signIn(_email.value.text, _password.value.text)

                            event.navController.navigate(Screen.HomeScreen.route) {
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

        }
    }
}