package com.example.baseproject.ui.authentication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.baseproject.R
import com.example.baseproject.databinding.FragmentLoginBinding
import com.example.baseproject.extension.makeLinks
import com.example.baseproject.navigation.AppNavigation
import com.example.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {

    @Inject
    lateinit var appNavigation: AppNavigation
    private val viewModel: LoginViewModel by viewModels()
    override fun getVM() = viewModel

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        binding.tvTitleNoAccount.makeLinks(
            Pair(getString(R.string.resgistry), View.OnClickListener {
                appNavigation.openLoginToRegisterScreen()
            })
        )

        binding.btnLogin.setOnClickListener {
            appNavigation.openLoginToHomeScreen()
        }
    }

    override fun bindingAction() {
        super.bindingAction()
    }

    override fun bindingStateView() {
        super.bindingStateView()
    }
}
