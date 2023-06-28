package com.example.baseproject.ui.home.friends

import androidx.lifecycle.SavedStateHandle
import com.example.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    val savedStateHandle : SavedStateHandle
) : BaseViewModel() {

}