package com.travels.android.journeys.domain

import io.reactivex.Single

interface PlacesRepository {
    fun getPlaces(query: String): Single<List<Place>>
    fun saveJourney(journey: Journey): Single<Journey>
}