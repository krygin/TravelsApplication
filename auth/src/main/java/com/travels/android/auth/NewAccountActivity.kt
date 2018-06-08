package com.travels.android.auth

import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.travels.android.base.TravelsApplication
import com.travels.android.base.di.ApplicationProvider

class NewAccountActivity : AppCompatActivity(), OnAuthenticationResultListener, ApplicationProvider {

    private var accountAuthenticatorResponse: AccountAuthenticatorResponse? = null
    private var resultBundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_account)
        accountAuthenticatorResponse = intent.getParcelableExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE)
        accountAuthenticatorResponse?.onRequestContinued()
    }

    override fun finish() {
        if (resultBundle != null)
            accountAuthenticatorResponse?.onResult(resultBundle)
        else
            accountAuthenticatorResponse?.onError(AccountManager.ERROR_CODE_CANCELED, "canceled")
        accountAuthenticatorResponse = null
        super.finish()
    }

    override fun onSuccess(authResult: AuthResult) {
        val intent = Intent()
        intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, authResult.username)
        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, authResult.accountType)
        resultBundle = intent.extras
        this.setResult(Activity.RESULT_OK, intent)
        this.finish()
    }

    override fun onFailure() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun provideApp(): TravelsApplication = application as TravelsApplication
}