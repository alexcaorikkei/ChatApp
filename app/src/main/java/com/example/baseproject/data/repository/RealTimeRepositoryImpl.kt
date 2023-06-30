package com.example.baseproject.data.repository

import android.util.Log
import com.example.baseproject.domain.model.Response
import com.example.baseproject.domain.repository.RealTimeRepository
import com.example.baseproject.ui.home.friends.model.FriendModel
import com.example.baseproject.ui.home.friends.model.FriendState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class RealTimeRepositoryImpl @Inject constructor(private val database: FirebaseDatabase) : RealTimeRepository {
    override suspend fun writeNewUser(
        userId: String,
        displayName: String,
        email: String
    ): Response<Boolean> {
        return try {
            database.reference.child("users").child(userId).apply {
                child("profile").apply {
                    child("display_name").setValue(displayName)
                    child("email").setValue(email)
                    child("profile_picture").setValue("")
                }
                child("added").setValue(listOf<FriendModel>())
                child("request").setValue(listOf<FriendModel>())

            }
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun searchForFriend(uid: String, query: String): Response<List<FriendModel>> {
        return try {
            val listFriends = mutableListOf<FriendModel>()
            searchByState(FriendState.ADDED, uid, query, listFriends)
            searchByState(FriendState.REQUEST, uid, query, listFriends)
            searchByState(FriendState.FRIEND, uid, query, listFriends)
            searchAll(query, listFriends)
            Response.Success(listFriends)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    private fun String.notIn(listFriends: List<FriendModel>): Boolean {
        listFriends.forEach {
            if (it.displayName == this) {
                return false
            }
        }
        return true
    }

    private suspend fun searchByState(state: FriendState, uid: String, query: String, listFriends: MutableList<FriendModel>) {
        val friends = database.reference.child("users").child(uid).child(state.toString()).get().await()
        for(friend in friends.children) {
            if(friend is Map<*, *> && friend["display_name"].toString().contains(query)) {
                listFriends.add(
                    FriendModel(
                        friend["uid"].toString(),
                        friend["display_name"].toString(),
                        friend["profile_picture"].toString(),
                        state,
                    )
                )
            }
        }
    }
    private suspend fun searchAll(query: String, listFriends: MutableList<FriendModel>) {
        val friends = database.reference.child("users").get().await()
        friends.children.forEach { userSnapshot ->
            if(userSnapshot.key.toString().notIn(listFriends) && userSnapshot.child("profile").child("display_name").value.toString().contains(query)) {
                listFriends.add(
                    FriendModel(
                        userSnapshot.key.toString(),
                        userSnapshot.child("profile").child("display_name").value.toString(),
                        userSnapshot.child("profile").child("profile_picture").value.toString(),
                        FriendState.NONE
                    )
                )
            }
        }
    }
}