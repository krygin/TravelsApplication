package com.travels.android.app.di

import com.travels.android.app.navigation.NavigationController
import com.travels.android.app.navigation.NavigationControllerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {
    @Provides
    @Singleton
    fun provideNavigationController(): NavigationController = NavigationControllerImpl()
}