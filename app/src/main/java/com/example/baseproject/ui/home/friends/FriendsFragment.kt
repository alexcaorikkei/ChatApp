package com.example.baseproject.ui.home.friends

import androidx.fragment.app.viewModels
import com.example.baseproject.R
import com.example.baseproject.databinding.FragmentFriendsBinding
import com.example.baseproject.navigation.AppNavigation
import com.example.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FriendsFragment : BaseFragment<FragmentFriendsBinding, FriendsViewModel>(R.layout.fragment_friends) {
    @Inject
    lateinit var appNavigation: AppNavigation
    val viewModel: FriendsViewModel by viewModels()
    override fun getVM() = viewModel
}