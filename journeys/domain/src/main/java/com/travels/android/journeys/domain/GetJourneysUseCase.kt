package com.travels.android.journeys.domain

import com.travels.android.api.journeys.Journey
import com.travels.android.api.journeys.PlacesRepository
import io.reactivex.Single

class GetJourneysUseCase(private val placesRepository: PlacesRepository) {

    fun execute(): Single<List<Journey>> {
        return placesRepository.getJourneys()
    }
}