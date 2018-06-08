package com.travels.android.base.persistence.user

import io.reactivex.Single

interface UserRepository {
    fun saveUser(user: User): Single<Long>
    fun getUser(userId: Long): Single<User>
}
