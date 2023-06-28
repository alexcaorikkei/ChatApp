package com.example.baseproject.ui.home.friends

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.baseproject.R
import com.example.baseproject.databinding.FragmentAllTabBinding
import com.example.baseproject.navigation.AppNavigation
import com.example.baseproject.ui.home.HomeViewModel
import com.example.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AllTabFragment : BaseFragment<FragmentAllTabBinding, AllTabViewModel>(R.layout.fragment_all_tab) {
    @Inject
    lateinit var appNavigation: AppNavigation
    private val viewModel: AllTabViewModel by viewModels()
    override fun getVM() = viewModel
}