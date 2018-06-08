package com.travels.android.auth.domain

import com.travels.android.base.persistence.user.User
import io.reactivex.Single

interface AccountManager {
    fun registerUser(user: User): Single<RegisterUserResult>
}