package com.example.baseproject.ui.home.friends


import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.baseproject.R
import com.example.baseproject.databinding.FragmentFriendsBinding
import com.example.baseproject.domain.model.Response
import com.example.baseproject.navigation.AppNavigation
import com.example.baseproject.ui.home.friends.adapter.FriendsNavigationAdapter
import com.example.core.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class FriendsFragment : BaseFragment<FragmentFriendsBinding, FriendsViewModel>(R.layout.fragment_friends) {
    @Inject
    lateinit var appNavigation: AppNavigation
    val viewModel: FriendsViewModel by activityViewModels()
    override fun getVM() = viewModel

    override fun bindingStateView() {
        super.bindingStateView()
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

    override fun bindingAction() {
        super.bindingAction()
        listen()
    }

    private fun listen() {
        binding.apply {
            edtSearch.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    friendsViewPager.setCurrentItem(1, true)
                }
            }

            edtSearch.addTextChangedListener {
                viewModel.searchForFriend(it.toString())
            }
        }
    }
}

