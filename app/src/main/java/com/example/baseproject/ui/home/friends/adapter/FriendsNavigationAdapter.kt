package com.example.baseproject.ui.home.friends.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.baseproject.ui.home.friends.AllTabFragment
import com.example.baseproject.ui.home.friends.FriendTabFragment

class FriendsNavigationAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> FriendTabFragment()
            1 -> AllTabFragment()
            2 -> FriendTabFragment()
            else -> FriendTabFragment()
        }
    }

}