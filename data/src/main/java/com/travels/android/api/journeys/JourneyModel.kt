package com.travels.android.api.journeys

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "journey")
class JourneyModel(
    @ColumnInfo(name = "uid") @PrimaryKey(autoGenerate = true) val uid: Long?
)