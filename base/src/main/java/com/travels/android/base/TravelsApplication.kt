package com.travels.android.base

import android.app.Application
import com.travels.android.base.di.ApplicationComponent
import com.travels.android.base.di.BaseSubComponent
import com.travels.android.base.di.DaggerApplicationComponent

class TravelsApplication: Application() {

    private lateinit var appComponent: ApplicationComponent

    fun provideBaseSubComponent(): BaseSubComponent = appComponent.baseSubcomponentBuilder().build()

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder().create(this) as ApplicationComponent
    }
}