package com.example.baseproject.domain.repository

import com.example.baseproject.domain.model.Response
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun firebaseSignUp(email: String, password: String): Response<Boolean>
    suspend fun firebaseLogin(email: String, password: String): Response<Boolean>
    suspend fun sendEmailVerification(): Response<Boolean>
    suspend fun sendPasswordResetEmail(email: String): Response<Boolean>

    fun logOut()
}