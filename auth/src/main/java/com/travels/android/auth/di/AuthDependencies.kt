package com.travels.android.auth.di

import android.content.Context
import com.travels.android.base.di.ComponentDependencies
import com.travels.android.base.persistence.TravelsDatabase
import retrofit2.Retrofit

interface AuthDependencies: ComponentDependencies {
    fun context(): Context
    fun retrofit(): Retrofit
    fun database(): TravelsDatabase
}