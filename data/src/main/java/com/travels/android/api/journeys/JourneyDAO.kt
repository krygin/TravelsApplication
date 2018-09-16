package com.travels.android.api.journeys

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface JourneyDAO {
    @Query("SELECT * FROM journey")
    fun allJourneys(): List<JourneyModel>

    @Insert
    fun saveJourney(journeyModel: JourneyModel): Long

    @Query("SELECT * FROM journey WHERE uid = :journeyId")
    fun getUser(journeyId: Long): JourneyModel
}