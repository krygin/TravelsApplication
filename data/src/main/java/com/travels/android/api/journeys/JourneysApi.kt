package com.travels.android.api.journeys

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface JourneysApi {
    @GET("/sync/api/position/autocomplete")
    fun getPlaces(@Query("input") query: String): Observable<PlacesResponse>

    @POST("/sync/api/journey/create")
    fun createJourney(@Body journey: JourneyApiModel): Observable<CreateJourneyResponse>
}