package com.travels.android.api.journeys

import io.reactivex.Single

interface PlacesRepository {
    fun getPlaces(query: String): Single<List<Place>>
    fun saveJourney(journey: Journey): Single<Journey>
    fun getJourneys(): Single<List<Journey>>
}