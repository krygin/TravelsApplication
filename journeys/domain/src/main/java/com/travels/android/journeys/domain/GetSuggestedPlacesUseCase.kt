package com.travels.android.journeys.domain

import com.travels.android.api.journeys.Place
import com.travels.android.api.journeys.PlacesRepository
import io.reactivex.Single

class GetSuggestedPlacesUseCase(private val placesRepository: PlacesRepository) {

    fun execute(query: String): Single<List<Place>> {
        return placesRepository.getPlaces(query)
    }
}