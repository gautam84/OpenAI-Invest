package com.openaiinvest.app.domain.repository

import com.openaiinvest.app.common.AuthEvent
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
   suspend fun register(email: String, password: String)
   suspend fun signIn(email: String, password: String)
   suspend fun resetPassword(email: String)
   suspend fun signOut()
}
