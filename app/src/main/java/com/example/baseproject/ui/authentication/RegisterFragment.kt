package com.example.baseproject.ui.authentication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.baseproject.R
import com.example.baseproject.databinding.FragmentRegisterBinding
import com.example.baseproject.domain.model.Response
import com.example.baseproject.extension.makeLinks
import com.example.baseproject.navigation.AppNavigation
import com.example.core.base.BaseFragment
import com.example.core.utils.toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalArgumentException
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment() : BaseFragment<FragmentRegisterBinding, RegisterViewModel>(R.layout.fragment_register) {


    @Inject
    lateinit var appNavigation: AppNavigation
    private val viewModel: RegisterViewModel by viewModels()
    override fun getVM() = viewModel

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        binding.cbTerm.makeLinks(
            Pair(getString(R.string.policies), View.OnClickListener {
                "policies".toast(requireContext())
            }),
            Pair(getString(R.string.terms), View.OnClickListener {
                "terms".toast(requireContext())
            })
        )
        binding.tvTitleHadAccount.makeLinks(
            Pair(getString(R.string.login), View.OnClickListener {
                appNavigation.openRegisterToLoginScreen()
            })
        )
    }

    override fun bindingAction() {
        super.bindingAction()
        listen()
        viewModel.apply {
            signUpResponse.observe(this@RegisterFragment) { response ->
                when (response) {
                    is Response.Loading -> {}
                    is Response.Success -> {
                        resources.getString(R.string.sign_up_successfully).toast(requireContext())
                        appNavigation.openRegisterToLoginScreen()
                    }
                    is Response.Failure -> {
                        when(response.e) {
                            is FirebaseAuthUserCollisionException -> {
                                resources.getString(R.string.email_already_exists).toast(requireContext())
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

    override fun bindingStateView() {
        super.bindingStateView()
        viewModel.apply {
            signUpResponse.observe(this@RegisterFragment) { response ->
                when (response) {
                    is Response.Loading -> {
                        binding.btnRegister.isEnabled = false
                        binding.edtEmail.isEnabled = false
                        binding.edtPassword.isEnabled = false
                        binding.edtName.isEnabled = false
                        binding.includeProgress.visibility = View.VISIBLE
                    }
                    is Response.Success -> {
                        binding.btnRegister.isEnabled = true
                        binding.edtEmail.isEnabled = true
                        binding.edtPassword.isEnabled = true
                        binding.edtName.isEnabled = true
                        binding.includeProgress.visibility = View.GONE
                    }
                    is Response.Failure -> {
                        binding.btnRegister.isEnabled = true
                        binding.edtEmail.isEnabled = true
                        binding.edtPassword.isEnabled = true
                        binding.edtName.isEnabled = true
                        binding.includeProgress.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun listen() {
        binding.btnRegister.setOnClickListener {
            viewModel.signUp(
                binding.edtEmail.text.toString(),
                binding.edtPassword.text.toString(),
                binding.edtName.text.toString()
            )
        }
    }
}