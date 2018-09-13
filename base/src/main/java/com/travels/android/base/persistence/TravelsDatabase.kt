package com.travels.android.base.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.travels.android.base.persistence.user.UserDAO
import com.travels.android.base.persistence.user.UserModel
import com.travels.android.base.persistence.journey.JourneyDAO
import com.travels.android.base.persistence.journey.JourneyModel

@Database(entities = [UserModel::class, JourneyModel::class], version = 2, exportSchema = false)
abstract class TravelsDatabase: RoomDatabase() {
    abstract fun userDao(): UserDAO

    abstract fun journeyDao(): JourneyDAO
}