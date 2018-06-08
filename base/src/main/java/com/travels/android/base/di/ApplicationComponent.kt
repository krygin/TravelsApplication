package com.travels.android.base.di

import com.travels.android.base.TravelsApplication
import dagger.Component
import dagger.android.AndroidInjector

@Component
interface ApplicationComponent: AndroidInjector<TravelsApplication> {

    fun baseSubcomponentBuilder(): BaseSubComponent.Builder

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TravelsApplication>()

}