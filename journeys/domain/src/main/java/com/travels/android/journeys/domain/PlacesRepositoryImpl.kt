package com.travels.android.journeys.domain

import com.travels.android.api.journeys.JourneyDAO
import com.travels.android.api.journeys.JourneysApi
import io.reactivex.Single

class PlacesRepositoryImpl(private val journeysApi: JourneysApi, private val journeyDao: JourneyDAO) : PlacesRepository {

    override fun getPlaces(query: String): Single<List<Place>> {
        return journeysApi
                .getPlaces(query)
                .map { placesResponse -> placesResponse.places.map { Place("", it.description, Location(0.0, 0.0)) } }
                .singleOrError()
    }

    override fun saveJourney(journey: Journey): Single<Journey> {
        return journeysApi
                .createJourney(journey.toJourneyApiModel())
                .map { it.journey.toJourney() }
                .singleOrError()
    }

}