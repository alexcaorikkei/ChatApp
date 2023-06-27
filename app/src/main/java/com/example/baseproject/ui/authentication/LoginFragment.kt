package com.example.baseproject.ui.authentication

import android.os.Bundle
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.baseproject.R
import com.example.baseproject.databinding.FragmentLoginBinding
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
    }

    override fun bindingAction() {
        super.bindingAction()
        binding.tvTitleNoAccount.makeLinks(
            Pair(getString(R.string.resgistry), View.OnClickListener {
                appNavigation.openLoginToRegisterScreen()
            })
        )
    }

    override fun bindingStateView() {
        super.bindingStateView()
    }

    private fun TextView.makeLinks(vararg links: Pair<String, View.OnClickListener>) {
        val spannableString = SpannableString(this.text)
        for(link in links) {
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    widget.invalidate()
                    link.second.onClick(widget)
                }
                override fun updateDrawState(ds: TextPaint) {
                    ds.color = ds.linkColor
                    super.updateDrawState(ds)
                }
            }
            val start = this.text.findAnyOf(listOf(link.first))?.first ?: 0
            val end = start + link.first.length
            spannableString.setSpan(clickableSpan, start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        this.movementMethod = LinkMovementMethod.getInstance()
        this.setText(spannableString, TextView.BufferType.SPANNABLE)
    }
}
