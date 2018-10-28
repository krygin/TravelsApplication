package com.travels.android.main.search.di

import android.content.Context
import com.travels.android.base.di.ComponentDependencies
import com.travels.android.base.persistence.TravelsDatabase
import retrofit2.Retrofit

interface SearchJourneyDependencies: ComponentDependencies {
    fun context(): Context
    fun retrofit(): Retrofit
    fun database(): TravelsDatabase
}