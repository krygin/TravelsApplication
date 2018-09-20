package com.travels.android.api.journeys

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Single

@Dao
interface JourneyDAO {
    @Query("SELECT * FROM journey")
    fun allJourneys(): Single<List<JourneyModel>>

    @Insert
    fun saveJourney(journeyModel: JourneyModel): Single<Long>

    @Insert
    fun saveRoutes(vararg routeItemModel: RouteItemModel): Single<List<Long>>

    @Query("SELECT * FROM journey WHERE uid = :journeyId")
    fun getJourney(journeyId: Long): Single<JourneyModel>
}