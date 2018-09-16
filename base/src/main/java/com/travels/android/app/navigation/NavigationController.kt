package com.travels.android.app.navigation

import android.content.Context
import android.content.Intent

interface NavigationController {

    fun createJourneyActivityIntent(context: Context): Intent

    fun journeysFilterActivity(context: Context): Intent
}