package com.travels.android.main.search.create.domain

import io.reactivex.Single

class SaveJourneyUseCase(private val placesRepository: PlacesRepository) {

    fun execute(journey: Journey): Single<Journey> {
        return placesRepository.saveJourney(journey)
    }
}