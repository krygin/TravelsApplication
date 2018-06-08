package com.travels.android.auth

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.travels.android.auth.domain.LoginVKUseCase

class AuthViewModelFactory(private val loginVKUseCase: LoginVKUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(loginVKUseCase)
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
    }

}