package com.travels.android.base.di

import android.app.Application
import android.content.Context
import com.travels.android.base.TravelsApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideApplication(travelsApplication: TravelsApplication): Application = travelsApplication

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context = application.applicationContext

}