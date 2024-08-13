package com.openaiinvest.app.domain.model

data class Transaction(
    val date: String,
    val time: String,
    val usdtAddress: String,
    val amount: Double
)
