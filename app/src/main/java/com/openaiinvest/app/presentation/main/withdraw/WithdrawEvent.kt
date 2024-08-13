package com.openaiinvest.app.presentation.main.withdraw

import android.content.Context

sealed class WithdrawEvent {
    data class EnteredUSDTAddress(val value: String) : WithdrawEvent()
    data class EnteredAmount(val value: String) : WithdrawEvent()

    data class  Withdraw(val context: Context) : WithdrawEvent()
}
