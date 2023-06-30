package com.example.baseproject.ui.home.friends

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.baseproject.domain.model.Response
import com.example.baseproject.domain.repository.AuthRepository
import com.example.baseproject.domain.repository.RealTimeRepository
import com.example.baseproject.ui.home.friends.model.FriendModel
import com.example.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    val savedStateHandle : SavedStateHandle,
    private val authRepository: AuthRepository,
    private val realTimeRepository: RealTimeRepository
) : BaseViewModel() {
    private val _searchResponse = MutableLiveData<Response<List<FriendModel>>>()
    val searchResponse: LiveData<Response<List<FriendModel>>> = _searchResponse

    fun searchForFriend(query: String) {
        viewModelScope.launch {
            _searchResponse.value = Response.Loading
            _searchResponse.value = realTimeRepository.searchForFriend(authRepository.currentUser!!.uid,query)
            Timber.d("searchForFriend: ${_searchResponse.value}")
        }
    }
}