package com.example.baseproject.data.repository

import com.example.baseproject.domain.model.Response
import com.example.baseproject.domain.repository.RealTimeRepository
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class RealTimeRepositoryImpl @Inject constructor(private val database: FirebaseDatabase) : RealTimeRepository {
    override suspend fun writeNewUser(
        userId: String,
        displayName: String,
        email: String
    ): Response<Boolean> {
        return try {
            database.reference.child("users").child(userId).child("display_name").setValue(displayName)
            database.reference.child("users").child(userId).child("email").setValue(email)
            database.reference.child("users").child(userId).child("phone_number").setValue("")
            database.reference.child("users").child(userId).child("profile_picture").setValue("")
            database.reference.child("users").child(userId).child("status").setValue("")
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
}