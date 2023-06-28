package com.example.baseproject.ui.authentication

import androidx.lifecycle.SavedStateHandle
import com.example.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val savedStateHandle : SavedStateHandle
) :BaseViewModel() {

}