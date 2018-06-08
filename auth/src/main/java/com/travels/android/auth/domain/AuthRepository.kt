package com.travels.android.auth.domain

import io.reactivex.Single

interface AuthRepository {
    fun loginVK(code: String): Single<VKAuthInfo>
}