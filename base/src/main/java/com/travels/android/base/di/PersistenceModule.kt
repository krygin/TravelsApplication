package com.travels.android.base.di

import android.arch.persistence.room.Room
import android.content.Context
import com.travels.android.base.persistence.TravelsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Provides
    @Singleton
    fun provideTravelsDatabase(context: Context): TravelsDatabase {
        return Room.inMemoryDatabaseBuilder(context,
                TravelsDatabase::class.java).build()
    }
}