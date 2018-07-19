package com.travels.android.app.di

import android.app.Application
import android.content.Context
import com.travels.android.app.TravelsApplication
import dagger.Binds
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