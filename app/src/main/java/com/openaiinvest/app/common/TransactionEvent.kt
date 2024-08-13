package com.openaiinvest.app.common

sealed class TransactionEvent {
    object Success : TransactionEvent()
    data class Error(val errorMessage: String) : TransactionEvent()

    companion object {
        fun success(): TransactionEvent = Success
        fun error(errorMessage: String): TransactionEvent = Error(errorMessage)
    }
}

