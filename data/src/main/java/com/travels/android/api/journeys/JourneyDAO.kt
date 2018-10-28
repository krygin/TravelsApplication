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

    @Insert
    fun saveRoutes(vararg routeItemModel: RouteItemModel): List<Long>

    @Query("SELECT * FROM journey WHERE uid = :journeyId")
    fun getJourney(journeyId: Long): JourneyModel

    @Query("SELECT * FROM route WHERE uid IN (:routeIds)")
    fun getRoutes(routeIds: List<Long>): List<RouteItemModel>

    @Query("SELECT * FROM route WHERE journey_uid = :journeyId")
    fun getRoutes(journeyId: Long): List<RouteItemModel>
}