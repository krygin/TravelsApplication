package com.travels.android.app

import android.app.Application
import com.travels.android.app.di.DaggerApplicationComponent
import com.travels.android.app.navigation.NavigationController
import com.travels.android.app.navigation.NavigationControllerProvider
import com.travels.android.base.di.ComponentDependenciesProvider
import com.travels.android.base.di.HasComponentDependencies
import javax.inject.Inject

class TravelsApplication : Application(), HasComponentDependencies, NavigationControllerProvider {

    @Inject
    override lateinit var dependencies: ComponentDependenciesProvider

    @Inject
    override lateinit var navigationController: NavigationController

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder().create(this).build().inject(this)
    }
}