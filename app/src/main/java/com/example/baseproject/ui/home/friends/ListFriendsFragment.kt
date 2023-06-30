package com.example.baseproject.ui.home.friends

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.baseproject.R
import com.example.baseproject.databinding.FragmentListFriendsBinding
import com.example.baseproject.domain.model.Response
import com.example.baseproject.navigation.AppNavigation
import com.example.baseproject.ui.home.friends.adapter.FriendsRecycleViewAdapter
import com.example.baseproject.ui.home.friends.model.FriendModel
import com.example.baseproject.ui.home.friends.model.FriendState
import com.example.core.base.BaseFragment
import com.example.core.base.BaseFragmentNotRequireViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject
@AndroidEntryPoint
class ListFriendsFragment : BaseFragment<FragmentListFriendsBinding, FriendsViewModel>(R.layout.fragment_list_friends) {
    @Inject
    lateinit var appNavigation: AppNavigation
    val viewModel: FriendsViewModel by activityViewModels()
    override fun getVM() = viewModel

    override fun bindingAction() {
        super.bindingAction()
    }

    override fun bindingStateView() {
        super.bindingStateView()
        binding.rvFriends.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
        viewModel.searchResponse.observe(viewLifecycleOwner) { response ->
            when(response) {
                is Response.Loading -> {
                }
                is Response.Failure -> {
                }
                is Response.Success -> {
                    binding.rvFriends.adapter = FriendsRecycleViewAdapter(response.data as MutableList<FriendModel>)
                }
            }
        }
    }
}