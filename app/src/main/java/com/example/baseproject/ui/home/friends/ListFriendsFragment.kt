package com.example.baseproject.ui.home.friends

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baseproject.R
import com.example.baseproject.databinding.FragmentListFriendsBinding
import com.example.baseproject.navigation.AppNavigation
import com.example.baseproject.ui.home.friends.adapter.FriendsRecycleViewAdapter
import com.example.core.base.BaseFragmentNotRequireViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListFriendsFragment : BaseFragmentNotRequireViewModel<FragmentListFriendsBinding>(R.layout.fragment_list_friends) {
    @Inject
    lateinit var appNavigation: AppNavigation

    override fun bindingStateView() {
        super.bindingStateView()
        binding.rvFriends.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
        binding.rvFriends.adapter = FriendsRecycleViewAdapter(listOf(
            FriendModel("Nguyen Van A", "" ,false, FriendState.FRIEND),
            FriendModel("Nguyen Van B", "" ,false, FriendState.ADDED),
            FriendModel("Nguyen Van C", "" ,true, FriendState.REQUESTED),
            FriendModel("Nguyen Van D", "" ,false, FriendState.FRIEND),
            FriendModel("Nguyen Van A", "" ,false, FriendState.FRIEND),
            FriendModel("Nguyen Van B", "" ,false, FriendState.ADDED),
            FriendModel("Nguyen Van C", "" ,true, FriendState.REQUESTED),
            FriendModel("Nguyen Van D", "" ,false, FriendState.FRIEND),
            FriendModel("Nguyen Van A", "" ,false, FriendState.FRIEND),
            FriendModel("Nguyen Van B", "" ,false, FriendState.ADDED),
            FriendModel("Nguyen Van C", "" ,true, FriendState.REQUESTED),
            FriendModel("Nguyen Van D", "" ,false, FriendState.FRIEND),
            FriendModel("Nguyen Van A", "" ,false, FriendState.FRIEND),
            FriendModel("Nguyen Van B", "" ,false, FriendState.ADDED),
            FriendModel("Nguyen Van C", "" ,true, FriendState.REQUESTED),
            FriendModel("Nguyen Van D", "" ,false, FriendState.FRIEND),
            FriendModel("Nguyen Van A", "" ,false, FriendState.FRIEND),
            FriendModel("Nguyen Van B", "" ,false, FriendState.ADDED),
            FriendModel("Nguyen Van C", "" ,true, FriendState.REQUESTED),
            FriendModel("Nguyen Van D", "" ,false, FriendState.FRIEND),
            FriendModel("Nguyen Van A", "" ,false, FriendState.FRIEND),
            FriendModel("Nguyen Van B", "" ,false, FriendState.ADDED),
            FriendModel("Nguyen Van C", "" ,true, FriendState.REQUESTED),
            FriendModel("Nguyen Van D", "" ,false, FriendState.FRIEND),
        ))
    }
}