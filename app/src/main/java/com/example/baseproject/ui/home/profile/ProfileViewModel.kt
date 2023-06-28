package com.example.baseproject.ui.home.profile

import androidx.lifecycle.SavedStateHandle
import com.example.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val savedStateHandle : SavedStateHandle
) : BaseViewModel() {

}