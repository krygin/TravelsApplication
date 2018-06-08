package com.travels.android.base.persistence.user

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface UserDAO {
    @Query("SELECT * FROM user")
    fun allUsers(): List<UserModel>

    @Insert
    fun saveUser(userModel: UserModel): Long

    @Query("SELECT * FROM user WHERE uid = :userId")
    fun getUser(userId: Long): UserModel
}