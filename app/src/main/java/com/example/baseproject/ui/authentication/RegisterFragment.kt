package com.example.baseproject.ui.authentication

import android.os.Bundle
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.baseproject.R
import com.example.baseproject.databinding.FragmentRegisterBinding
import com.example.baseproject.extension.makeLinks
import com.example.baseproject.navigation.AppNavigation
import com.example.core.base.BaseFragment
import com.example.core.utils.toast
import dagger.hilt.android.AndroidEntryPoint
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
    }

    private fun listen() {
        binding.btnRegister.setOnClickListener {
            viewModel.signUp(
                binding.edtEmail.text.toString(),
                binding.edtPassword.text.toString(),
            )
        }
    }
}