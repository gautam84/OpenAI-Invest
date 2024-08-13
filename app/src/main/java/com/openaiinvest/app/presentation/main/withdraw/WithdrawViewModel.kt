package com.openaiinvest.app.presentation.main.withdraw

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openaiinvest.app.common.Response
import com.openaiinvest.app.domain.model.Transaction
import com.openaiinvest.app.domain.repository.MainRepository
import com.openaiinvest.app.presentation.common.OpenAIInvestTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class WithdrawViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _usdtAddress = mutableStateOf(
        OpenAIInvestTextFieldState()
    )
    val usdtAddress: State<OpenAIInvestTextFieldState> = _usdtAddress

    private val _enterAmount = mutableStateOf(
        OpenAIInvestTextFieldState()
    )
    val enterAmount: State<OpenAIInvestTextFieldState> = _enterAmount

    fun onEvent(
        event: WithdrawEvent
    ) {
        when (event) {
            is WithdrawEvent.EnteredUSDTAddress -> {

                _usdtAddress.value = usdtAddress.value.copy(
                    text = event.value
                )

            }

            is WithdrawEvent.EnteredAmount -> {
                _enterAmount.value = enterAmount.value.copy(
                    text = event.value
                )

            }

            is WithdrawEvent.Withdraw -> {
                viewModelScope.launch {
                    if (_enterAmount.value.text != "" && _usdtAddress.value.text != "") {
                        mainRepository.getUserData().collect { response ->
                            when (response) {
                                is Response.Success -> {
                                    if (
                                        ((0.016 * response.data!!.shares * 100) + (response.data.shares * 100)) + (response.data.tokens * 10) >= _enterAmount.value.text.toDouble() && _enterAmount.value.text.toDouble() != 0.0
                                    ) {
                                        try {

                                            mainRepository.addTransaction(
                                                Transaction(
                                                    date = SimpleDateFormat(
                                                        "hh:mm a",
                                                        Locale.getDefault()
                                                    ).format(Date()),
                                                    time = SimpleDateFormat(
                                                        "dd:MM:yyyy",
                                                        Locale.getDefault()
                                                    ).format(Date()),
                                                    usdtAddress = _usdtAddress.value.text,
                                                    amount = _enterAmount.value.text.toDouble()

                                                )
                                            )
                                            Toast.makeText(
                                                event.context,
                                                "Withdraw Request added!",
                                                Toast.LENGTH_SHORT
                                            )
                                                .show()
                                            _enterAmount.value = enterAmount.value.copy(
                                                text = ""
                                            )
                                            _usdtAddress.value = usdtAddress.value.copy(
                                                text = ""
                                            )
                                        } catch (e: Exception) {

                                            Toast.makeText(
                                                event.context,
                                                "Error occurred! Please try again",
                                                Toast.LENGTH_SHORT
                                            ).show()


                                        }
                                    } else {
                                        Toast.makeText(
                                            event.context,
                                            "Please Enter a valid amount.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                }

                                is Response.Error -> {
                                    Log.d("Tag", response.message.toString())

                                }

                                is Response.Loading -> {}
                            }
                        }
                    }
                    else {
                        Toast.makeText(
                            event.context,
                            "Please fill up all the fields.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

}
