package com.travels.android.auth

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import com.travels.android.auth.domain.LoginVKParams
import com.travels.android.auth.domain.LoginVKUseCase
import com.travels.android.base.domain.Response

class AuthViewModel(
        private val loginVKUseCase: LoginVKUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()

    val vkAuthLiveData: MutableLiveData<Response<AuthResult>> = MutableLiveData<Response<AuthResult>>()

    fun vkAuth(code: String) {
        disposables.add(
                loginVKUseCase.execute(LoginVKParams(code))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { vkAuthLiveData.value = Response.Loading() }
                        .subscribe(
                                { it -> vkAuthLiveData.value = Response.Success(AuthResult(it.user.username, it.accountType)) },
                                { it -> vkAuthLiveData.value = Response.Failure(it) }
                        )
        )
    }
}

data class AuthResult(val username: String, val accountType: String)