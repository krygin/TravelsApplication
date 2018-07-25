package com.travels.android.main.search.create.domain

import com.travels.android.design.widget.model.Location
import com.travels.android.design.widget.model.Place
import com.travels.android.main.search.create.networking.PlacesApi
import io.reactivex.Single

interface PlacesRepository {
    fun getPlaces(query: String): Single<List<Place>>
}

class PlacesRepositoryImpl(private val placesApi: PlacesApi) : PlacesRepository {
    override fun getPlaces(query: String): Single<List<Place>> {
        return placesApi
                .getPlaces(query)
                .map { placesResponse -> placesResponse.places.map { Place(Location(0.0, 0.0), it.description) } }
                .singleOrError()
    }

}