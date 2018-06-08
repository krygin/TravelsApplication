package com.travels.android.auth.domain

import android.os.Bundle
import com.travels.android.base.persistence.user.User
import io.reactivex.Single

class AccountManagerImpl(private val accountManager: android.accounts.AccountManager) : AccountManager {
    override fun registerUser(user: User): Single<RegisterUserResult> {
        val userData = Bundle().apply { putLong(EXTRA_USER_ID, user.userId) }
        accountManager.addAccountExplicitly(TravelsAccount(user.username), null, userData)
        return Single.just(RegisterUserResult(user, TRAVELS_ACCOUNT_TYPE))
    }

    fun userId(): Long {
        val account = accountManager.getAccountsByType(TRAVELS_ACCOUNT_TYPE).firstOrNull()
        val userData = accountManager.getUserData(account, EXTRA_USER_ID)
        return userData.toLong()
    }
}


private const val EXTRA_USER_ID = "EXTRA_USER_ID"
