package com.example.baseproject.domain.repository

import com.example.baseproject.domain.model.Response
import com.example.baseproject.ui.home.friends.model.FriendModel


interface RealTimeRepository {
    suspend fun writeNewUser(userId: String, displayName: String, email: String): Response<Boolean>

    suspend fun searchForFriend(uid: String, query: String): Response<List<FriendModel>>
}