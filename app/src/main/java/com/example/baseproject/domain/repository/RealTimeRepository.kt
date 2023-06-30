package com.example.baseproject.domain.repository

import com.example.baseproject.domain.model.Response


interface RealTimeRepository {
    suspend fun writeNewUser(userId: String, displayName: String, email: String): Response<Boolean>
}