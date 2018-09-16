package com.travels.android.journeys.create.di

import com.travels.android.base.di.PerActivity
import com.travels.android.journeys.create.CreateNewJourneyActivity
import dagger.Component

@PerActivity
@Component(
        dependencies = [CreateNewJourneyDependencies::class],
        modules = [CreateNewJourneyModule::class]

)
interface CreateNewJourneyComponent {

    fun inject(createNewJourneyActivity: CreateNewJourneyActivity)
}