package com.travels.android.base.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.travels.android.api.journeys.JourneyDAO
import com.travels.android.api.journeys.JourneyModel
import com.travels.android.api.journeys.PlaceModel
import com.travels.android.api.journeys.RouteItemModel
import com.travels.android.base.persistence.user.UserDAO
import com.travels.android.base.persistence.user.UserModel

@Database(entities = [UserModel::class,
    JourneyModel::class,
    RouteItemModel::class,
    PlaceModel::class
],
        version = 3,
        exportSchema = false
)
@TypeConverters(DateTimeConverter::class)
abstract class TravelsDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO

    abstract fun journeyDao(): JourneyDAO
}