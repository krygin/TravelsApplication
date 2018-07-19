package com.travels.android.main.search.create.di

import android.content.Context
import com.travels.android.base.di.ComponentDependencies
import com.travels.android.base.persistence.TravelsDatabase
import retrofit2.Retrofit

interface CreateNewJourneyDependencies: ComponentDependencies {
    fun context(): Context
    fun retrofit(): Retrofit
    fun database(): TravelsDatabase
}