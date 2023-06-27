package com.example.baseproject.ui.authentication

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.baseproject.R
import com.example.baseproject.databinding.FragmentRegisterBinding
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

        binding.tvTitleHadAccount?.setOnClickListener {
            appNavigation.openRegisterToLoginScreen()
        }
    }

    override fun getVM() = viewModel

    private fun CheckBox.makeLinks(vararg links: Pair<String, View.OnClickListener>) {
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