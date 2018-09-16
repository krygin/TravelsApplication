package com.travels.android.journeys.create.di

import com.travels.android.base.di.PerActivity
import com.travels.android.journeys.create.CreateJourneyActivity
import dagger.Component

@PerActivity
@Component(
        dependencies = [CreateNewJourneyDependencies::class],
        modules = [CreateNewJourneyModule::class]

)
interface CreateNewJourneyComponent {

    fun inject(createJourneyActivity: CreateJourneyActivity)
}