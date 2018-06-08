package com.travels.android.auth.domain

import android.accounts.Account

class TravelsAccount(val name: String) : Account(name, TRAVELS_ACCOUNT_TYPE)
public const val TRAVELS_ACCOUNT_TYPE = "ru.krygin.travels.account"