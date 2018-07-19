package com.travels.android.app

import android.app.Application
import com.travels.android.app.di.DaggerApplicationComponent
import com.travels.android.base.di.ComponentDependenciesProvider
import com.travels.android.base.di.HasComponentDependencies
import javax.inject.Inject

class TravelsApplication : Application(), HasComponentDependencies {

    @Inject
    override lateinit var dependencies: ComponentDependenciesProvider

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder().create(this).build().inject(this)
    }
}