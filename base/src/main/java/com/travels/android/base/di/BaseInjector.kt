package com.travels.android.base.di

import com.travels.android.base.TravelsApplication
import dagger.android.AndroidInjector

interface BaseInjector<T : ApplicationProvider> : AndroidInjector<T> {

    abstract class Builder<T : ApplicationProvider> : AndroidInjector.Builder<T>() {

        abstract operator fun plus(component: BaseSubComponent): Builder<T>

        fun inject(activity: T) {
            plus(activity.provideApp().provideBaseSubComponent())
            create(activity)
                    .inject(activity)
        }

    }

}

interface ApplicationProvider {

    fun provideApp(): TravelsApplication
}