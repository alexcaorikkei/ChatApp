package com.example.baseproject.ui.home.friends

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager.widget.PagerAdapter
import com.example.baseproject.R
import com.example.baseproject.databinding.FragmentFriendsBinding
import com.example.baseproject.navigation.AppNavigation
import com.example.baseproject.ui.home.friends.adapter.FriendsNavigationAdapter
import com.example.core.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FriendsFragment : BaseFragment<FragmentFriendsBinding, FriendsViewModel>(R.layout.fragment_friends) {
    @Inject
    lateinit var appNavigation: AppNavigation
    val viewModel: FriendsViewModel by viewModels()
    private lateinit var navController: NavController
    override fun getVM() = viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.friendsViewPager.adapter = FriendsNavigationAdapter(this)
        TabLayoutMediator(binding.friendsNav, binding.friendsViewPager) { tab, position ->
            tab.text = when(position) {
                0 -> "Friends"
                1 -> "All"
                2 -> "Requests"
                else -> "Friends"
            }
        }.attach()
    }
}

