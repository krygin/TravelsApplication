package com.travels.android.auth.domain


import com.travels.android.base.persistence.user.User
import io.reactivex.Single
import com.travels.android.auth.networking.AuthApi


class AuthRepositoryImpl(private val authApi: AuthApi): AuthRepository {

    override fun loginVK(code: String): Single<VKAuthInfo> {
        return authApi
                .loginVK(code)
                .map { vkAuthResponse -> VKAuthInfo(User(vkAuthResponse.vkProfile.id, vkAuthResponse.vkProfile.lastName)) }
                .singleOrError()
    }
}