package com.example.baseproject.ui.authentication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.baseproject.R
import com.example.baseproject.databinding.FragmentLoginBinding
import com.example.baseproject.domain.model.Response
import com.example.baseproject.extension.makeLinks
import com.example.baseproject.navigation.AppNavigation
import com.example.core.base.BaseFragment
import com.example.core.utils.toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalArgumentException
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

    override fun bindingStateView() {
        super.bindingStateView()
        viewModel.apply {
            signInResponse.observe(this@LoginFragment) { response ->
                when (response) {
                    is Response.Loading -> {
                        binding.btnLogin.isEnabled = false
                        binding.edtEmail.isEnabled = false
                        binding.edtPassword.isEnabled = false
                        binding.includeProgress.visibility = View.VISIBLE
                    }
                    is Response.Success -> {
                        binding.btnLogin.isEnabled = true
                        binding.edtEmail.isEnabled = true
                        binding.edtPassword.isEnabled = true
                        binding.includeProgress.visibility = View.GONE
                    }
                    is Response.Failure -> {
                        binding.btnLogin.isEnabled = true
                        binding.edtEmail.isEnabled = true
                        binding.edtPassword.isEnabled = true
                        binding.includeProgress.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun bindingAction() {
        super.bindingAction()
        listen()
        viewModel.apply {
            signInResponse.observe(this@LoginFragment) { response ->
                when (response) {
                    is Response.Loading -> {}
                    is Response.Success -> {
                        appNavigation.openLoginToHomeScreen()
                    }
                    is Response.Failure -> {
                        when(response.e) {
                            is FirebaseAuthInvalidCredentialsException -> {
                                resources.getString(R.string.email_or_password_is_incorrect).toast(requireContext())
                            }
                            is IllegalArgumentException -> {
                                resources.getString(R.string.email_or_password_is_empty).toast(requireContext())
                            }
                            is FirebaseNetworkException -> {
                                resources.getString(R.string.no_internet_connection).toast(requireContext())
                            }
                            else -> {
                                response.e.toString().toast(requireContext())
                            }
                        }
                    }
                }
            }
        }
    }

    private fun listen() {
        binding.apply {
            btnLogin.setOnClickListener {
                viewModel.signIn(
                    edtEmail.text.toString(),
                    edtPassword.text.toString()
                )
            }
        }
    }
}
