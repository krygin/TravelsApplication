package com.travels.android.main.search.di

import com.travels.android.base.di.PerActivity
import com.travels.android.main.search.list.ListSearchFragment
import dagger.Component

@PerActivity
@Component(
        modules = [SearchJourneyModule::class],
        dependencies = [SearchJourneyDependencies::class]
)

interface SearchJourneyComponent {
    fun inject(listSearchFragment: ListSearchFragment)
}