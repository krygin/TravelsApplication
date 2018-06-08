package com.travels.android.auth

import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class AppCompatAccountAuthenticatorActivity : AppCompatActivity() {
    private var accountAuthenticatorResponse: AccountAuthenticatorResponse? = null
    private var mResultBundle: Bundle? = null

    fun setAccountAuthenticatorResult(result: Bundle) {
        mResultBundle = result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        accountAuthenticatorResponse = intent.getParcelableExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE)
        accountAuthenticatorResponse?.onRequestContinued()
    }

    override fun finish() {
        if (mResultBundle != null)
            accountAuthenticatorResponse?.onResult(mResultBundle)
        else
            accountAuthenticatorResponse?.onError(AccountManager.ERROR_CODE_CANCELED, "canceled")
        accountAuthenticatorResponse = null
        super.finish()
    }
}