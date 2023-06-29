package com.example.baseproject.ui.authentication

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.baseproject.domain.model.Response
import com.example.baseproject.domain.repository.AuthRepository
import com.example.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val savedStateHandle : SavedStateHandle,
    private val authRepository: AuthRepository
) :BaseViewModel() {
    private var response: Response<Boolean> = Response.Success(false)
    private var sentEmailVerification:Response<Boolean> = Response.Success(false)
    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            response = Response.Loading
            response = authRepository.firebaseSignUp(email, password)
        }
    }

    fun sendEmailVerification() {
        viewModelScope.launch {
            sentEmailVerification = Response.Loading
            sentEmailVerification = authRepository.sendEmailVerification()
        }
    }
}