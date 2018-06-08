package com.travels.android.base.persistence.user

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user")
class UserModel(
        @ColumnInfo(name = "uid") @PrimaryKey val uid: Long? = null,
        @ColumnInfo(name="server_id") val serverId: Long,
        @ColumnInfo(name="first_name") val firstName: String? = null,
        @ColumnInfo(name="last_name") val lastName: String? = null,
        @ColumnInfo(name="username") val username: String? = null
)