package com.openaiinvest.app.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.openaiinvest.app.domain.model.UserData
import com.openaiinvest.app.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val auth: FirebaseAuth, private val usersRef: CollectionReference
) : AuthRepository {

    override suspend fun register(email: String, password: String) {


        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            auth.currentUser?.sendEmailVerification()

            try {
                usersRef.document(auth.uid!!).set(
                    UserData(
                        uid = auth.uid!!, email = email, tokens = 0, shares = 0
                    )
                )
            } catch (e: Exception) {
                throw e
            }


        } catch (e: Exception) {
            throw e

        }
    }

    override suspend fun signIn(email: String, password: String) {
        try {
            auth.signInWithEmailAndPassword(email, password).await()


        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun resetPassword(email: String) {
        try {
            auth.sendPasswordResetEmail(email).await()


        } catch (e: Exception) {
            throw e
        }    }

    override suspend fun signOut() {
        auth.signOut()
    }

}