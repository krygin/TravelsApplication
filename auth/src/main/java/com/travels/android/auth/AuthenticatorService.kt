package com.travels.android.auth

import android.app.Service
import android.content.Intent
import android.os.IBinder

class AuthenticatorService: Service() {
    private lateinit var authenticator: TravelsAccountAuthenticator

    override fun onCreate() {
        super.onCreate()
        authenticator = TravelsAccountAuthenticator(this)
    }

    override fun onBind(intent: Intent?): IBinder {
        return authenticator.iBinder
    }
}