package com.example.baseproject.ui.home.friends

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.baseproject.R
import com.example.baseproject.databinding.FragmentFriendTabBinding
import com.example.baseproject.databinding.FragmentRequestTabBinding
import com.example.baseproject.navigation.AppNavigation
import com.example.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class RequestTabFragment : BaseFragment<FragmentRequestTabBinding, RequestTabViewModel>(R.layout.fragment_request_tab) {
    @Inject
    lateinit var appNavigation: AppNavigation
    private val viewModel: RequestTabViewModel by viewModels()
    override fun getVM() = viewModel
}