package com.example.baseproject.ui.home.friends

enum class FriendState {
    FRIEND,
    ADDED,
    REQUESTED
}

data class FriendModel(
    val name: String,
    val avatar: String,
    val isOnline: Boolean,
    val state: FriendState
)