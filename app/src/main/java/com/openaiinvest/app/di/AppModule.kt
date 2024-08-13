package com.openaiinvest.app.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.openaiinvest.app.data.repository.AuthRepositoryImpl
import com.openaiinvest.app.data.repository.MainRepositoryImpl
import com.openaiinvest.app.domain.repository.AuthRepository
import com.openaiinvest.app.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFirebaseAuthInstance(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun providesFirestoreInstance() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun providesUsersRef(firestore: FirebaseFirestore) =
        firestore.collection("users")

    @Provides
    @Singleton
    fun providesAuthRepository(auth: FirebaseAuth, userRef: CollectionReference): AuthRepository =
        AuthRepositoryImpl(auth, userRef)

    @Provides
    @Singleton
    fun providesMainRepository(auth: FirebaseAuth, userRef: CollectionReference): MainRepository =
        MainRepositoryImpl(auth, userRef)

}