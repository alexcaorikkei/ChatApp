package com.example.baseproject.ui.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.baseproject.domain.model.Response
import com.example.baseproject.domain.repository.AuthRepository
import com.example.baseproject.domain.repository.RealTimeRepository
import com.example.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val savedStateHandle : SavedStateHandle,
    private val authRepository: AuthRepository,
    private val realTimeRepository: RealTimeRepository
) :BaseViewModel() {
    private var _signUpResponse: MutableLiveData<Response<Boolean>> = MutableLiveData()
    val signUpResponse: LiveData<Response<Boolean>> get() = _signUpResponse

    private var _sentEmailResponse: MutableLiveData<Response<Boolean>> = MutableLiveData()
    val sentEmailResponse: LiveData<Response<Boolean>> get() = _sentEmailResponse

    private var _createNewUserResponse: MutableLiveData<Response<Boolean>> = MutableLiveData()
    val createNewUserResponse: LiveData<Response<Boolean>> get() = _createNewUserResponse

    private var _validator: MutableLiveData<Boolean> = MutableLiveData()
    val validator: LiveData<Boolean> get() = _validator


    private var _isValidEmail = false
    private var _isValidPassword = false
    private var _isValidDisplayName = false
    private var _isChecked = false
    fun setValidState(
        isValidEmail: Boolean? = _isValidEmail,
        isValidPassword: Boolean? = _isValidPassword,
        isValidDisplayName: Boolean? = _isValidDisplayName,
        isChecked: Boolean? = _isChecked) {
        _isValidEmail = isValidEmail!!
        _isValidPassword = isValidPassword!!
        _isValidDisplayName = isValidDisplayName!!
        _isChecked = isChecked!!
        _validator.value = _isValidEmail && _isValidPassword && _isValidDisplayName && _isChecked
    }

    fun signUp(email: String, password: String, displayName: String) {
        viewModelScope.launch {
            _signUpResponse.value = Response.Loading
            _signUpResponse.value = authRepository.firebaseSignUp(email, password)
            if (_signUpResponse.value is Response.Success) {
                sendEmailVerification()
                createNewUser(displayName, email)
            }
        }
    }

    private fun sendEmailVerification() {
        viewModelScope.launch {
            _sentEmailResponse.value = Response.Loading
            _sentEmailResponse.value = authRepository.sendEmailVerification()
        }
    }

    private fun createNewUser(displayName: String, email: String) {
        viewModelScope.launch {
            _createNewUserResponse.value = Response.Loading
            _createNewUserResponse.value = realTimeRepository.writeNewUser(authRepository.currentUser!!.uid, displayName, email)
        }
    }
}