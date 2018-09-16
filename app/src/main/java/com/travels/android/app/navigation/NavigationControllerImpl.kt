package com.travels.android.app.navigation

import android.content.Context
import android.content.Intent
import com.travels.android.journeys.create.createCreateJourneyActivityIntent
import com.travels.android.journeys.filter.createJourneysFilterActivity

class NavigationControllerImpl: NavigationController {

    override fun createJourneyActivityIntent(context: Context): Intent = createCreateJourneyActivityIntent(context)

    override fun journeysFilterActivity(context: Context): Intent = createJourneysFilterActivity(context)

}