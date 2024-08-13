package com.openaiinvest.app.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.openaiinvest.app.common.AuthEvent
import com.openaiinvest.app.common.Response
import com.openaiinvest.app.common.TransactionEvent
import com.openaiinvest.app.domain.model.Transaction
import com.openaiinvest.app.domain.model.UserData
import com.openaiinvest.app.domain.repository.MainRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await


class MainRepositoryImpl(
    private val auth: FirebaseAuth, private val usersRef: CollectionReference
) : MainRepository {


    override fun getUserData(): Flow<Response<UserData>> = callbackFlow {
        trySend(Response.Loading<UserData>())

        val snapshotListener = usersRef.document(auth.uid!!).addSnapshotListener { snapshot, e ->
            if (e != null) {
                trySend(Response.Error<UserData>(message = e.localizedMessage ?: "Error"))
                close(e)

                return@addSnapshotListener
            }

            val userData = snapshot?.toObject(UserData::class.java)

            trySend(Response.Success<UserData>(data = userData))


        }

        awaitClose {
            snapshotListener.remove()
        }

    }


    override suspend fun addTransaction(transaction: Transaction): TransactionEvent {
        return try {
            usersRef.document(auth.uid!!).collection("transactions")
                .document(System.currentTimeMillis().toString()).set(
                    transaction
                )
            TransactionEvent.success()

        } catch (e: Exception) {

            TransactionEvent.error(e.message.toString())
        }
    }
}