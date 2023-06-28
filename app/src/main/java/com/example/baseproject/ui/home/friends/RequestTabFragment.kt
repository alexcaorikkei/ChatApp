package com.example.baseproject.ui.home.friends

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baseproject.R

class RequestTabFragment : Fragment() {

    companion object {
        fun newInstance() = RequestTabFragment()
    }

    private lateinit var viewModel: RequestTabViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_request_tab, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RequestTabViewModel::class.java)
        // TODO: Use the ViewModel
    }

}