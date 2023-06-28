package com.example.baseproject.ui.home.messages

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.baseproject.R
import com.example.baseproject.databinding.FragmentMessagesBinding
import com.example.baseproject.navigation.AppNavigation
import com.example.baseproject.ui.home.friends.FriendsViewModel
import com.example.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MessagesFragment : BaseFragment<FragmentMessagesBinding, MessagesViewModel>(R.layout.fragment_messages) {
    @Inject
    lateinit var appNavigation: AppNavigation
    val viewModel: MessagesViewModel by viewModels()
    override fun getVM() = viewModel
}