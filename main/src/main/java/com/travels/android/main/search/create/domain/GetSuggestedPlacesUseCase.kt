package com.travels.android.main.search.create.domain

import com.travels.android.main.search.core.Place
import io.reactivex.Single

class GetSuggestedPlacesUseCase(private val placesRepository: PlacesRepository) {

    fun execute(query: String): Single<List<Place>> {
        return placesRepository.getPlaces(query)
    }
}