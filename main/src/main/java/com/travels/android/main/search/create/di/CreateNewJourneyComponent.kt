package com.travels.android.main.search.create.di

import com.travels.android.base.di.BaseInjector
import com.travels.android.base.di.BaseSubComponent
import com.travels.android.base.di.PerActivity
import com.travels.android.main.search.create.CreateNewJourneyActivity
import dagger.Component

@PerActivity
@Component(
        dependencies = [BaseSubComponent::class],
        modules = [CreateNewJourneyModule::class]

)
interface CreateNewJourneyComponent : BaseInjector<CreateNewJourneyActivity> {

    @Component.Builder
    abstract class Builder : BaseInjector.Builder<CreateNewJourneyActivity>()
}