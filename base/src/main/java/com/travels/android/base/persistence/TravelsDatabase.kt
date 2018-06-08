package com.travels.android.base.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.travels.android.base.persistence.user.UserDAO
import com.travels.android.base.persistence.user.UserModel

@Database(entities = [UserModel::class], version = 1, exportSchema = false)
abstract class TravelsDatabase: RoomDatabase() {
    abstract fun userDao(): UserDAO
}