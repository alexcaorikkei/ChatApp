package com.example.baseproject.ui.home.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.baseproject.R
import com.example.baseproject.databinding.FragmentProfileBinding
import com.example.baseproject.navigation.AppNavigation
import com.example.baseproject.ui.home.friends.FriendsViewModel
import com.example.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>(R.layout.fragment_profile) {
    @Inject
    lateinit var appNavigation: AppNavigation
    val viewModel: ProfileViewModel by viewModels()
    override fun getVM() = viewModel

    override fun bindingAction() {
        super.bindingAction()
        binding.btnEditProfile.setOnClickListener() {
            appNavigation.openHomeToEditProfileScreen()
        }
    }
}