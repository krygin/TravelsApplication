package com.travels.android.base.di

import android.content.Context
import com.travels.android.base.persistence.TravelsDatabase
import dagger.Subcomponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = [ApplicationModule::class, NetworkModule::class, PersistenceModule::class])
interface BaseSubComponent {

    val context: Context

    val travlesDatabase: TravelsDatabase

    val retrofit: Retrofit

    @Subcomponent.Builder
    interface Builder {
        fun build(): BaseSubComponent
    }
}