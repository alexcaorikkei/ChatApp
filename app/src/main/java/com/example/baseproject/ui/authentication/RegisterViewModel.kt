package com.example.baseproject.ui.authentication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.baseproject.domain.model.Response
import com.example.baseproject.domain.repository.AuthRepository
import com.example.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val savedStateHandle : SavedStateHandle,
    private val authRepository: AuthRepository
) :BaseViewModel() {
    private var _response: MutableLiveData<Response<Boolean>> = MutableLiveData()
    val response: LiveData<Response<Boolean>> get() = _response
    private var _sentEmailVerification: MutableLiveData<Response<Boolean>> = MutableLiveData()
    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            _response.value = Response.Loading
            _response.value = authRepository.firebaseSignUp(email, password)
            if (_response.value is Response.Success) {
                sendEmailVerification()
            }
        }
    }

    private fun sendEmailVerification() {
        viewModelScope.launch {
            _sentEmailVerification.value = Response.Loading
            _sentEmailVerification.value = authRepository.sendEmailVerification()
        }
    }
}