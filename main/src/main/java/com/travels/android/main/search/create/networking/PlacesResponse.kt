package com.travels.android.main.search.create.networking

import com.squareup.moshi.Json

data class PlacesResponse(
        @Json(name = "status") val status: String,
        @Json(name = "data") val places: List<Place>
)

data class Place(
        @Json(name = "place_id") val googlePlaceId: String,
        @Json(name = "id") val id: String,
        @Json(name = "inputs") val inputs: List<String>,
        @Json(name = "description") val description: String
)