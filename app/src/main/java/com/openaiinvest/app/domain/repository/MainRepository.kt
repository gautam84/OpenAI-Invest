package com.openaiinvest.app.domain.repository

import com.openaiinvest.app.common.Response
import com.openaiinvest.app.common.TransactionEvent
import com.openaiinvest.app.domain.model.Transaction
import com.openaiinvest.app.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getUserData(): Flow<Response<UserData>>
    suspend fun addTransaction(transaction: Transaction) : TransactionEvent
}