package com.example.baseproject.helper

import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.widget.AppCompatEditText

fun addPasswordValidator(whenWeak: String, whenEmpty: String, editText: AppCompatEditText) {
    editText.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            if(s.isNullOrEmpty()) {
                editText.error = whenEmpty
            } else if(s.length < 8) {
                editText.error = whenWeak
            } else {
                editText.error = null
            }
        }
    })
}

fun addEmailValidator(whenEmpty: String, whenInvalid: String, editText: AppCompatEditText) {
    editText.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if(s.isNullOrEmpty()) {
                editText.error = whenEmpty
            } else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                editText.error = whenInvalid
            } else {
                editText.error = null
            }
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

fun addNameValidator(whenEmpty: String, editText: AppCompatEditText) {
    editText.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if(s.isNullOrEmpty()) {
                editText.error = whenEmpty
            } else {
                editText.error = null
            }
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}