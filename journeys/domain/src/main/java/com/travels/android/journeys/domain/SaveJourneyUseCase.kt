package com.travels.android.journeys.domain

import com.travels.android.api.journeys.Journey
import com.travels.android.api.journeys.PlacesRepository
import io.reactivex.Single

class SaveJourneyUseCase(private val placesRepository: PlacesRepository) {

    fun execute(journey: Journey): Single<Journey> {
        return placesRepository.saveJourney(journey)
    }
}