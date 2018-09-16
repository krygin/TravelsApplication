package com.travels.android.journeys.domain

import io.reactivex.Single

class GetSuggestedPlacesUseCase(private val placesRepository: PlacesRepository) {

    fun execute(query: String): Single<List<Place>> {
        return placesRepository.getPlaces(query)
    }
}