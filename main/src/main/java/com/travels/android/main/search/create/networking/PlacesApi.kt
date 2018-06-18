package com.travels.android.main.search.create.networking

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApi {
    @GET("/sync/api/position/autocomplete")
    fun getPlaces(@Query("input") query: String) : Observable<PlacesResponse>
}