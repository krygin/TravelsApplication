package com.travels.android.base.persistence

import android.arch.persistence.room.TypeConverter
import java.util.Date


class DateTimeConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}