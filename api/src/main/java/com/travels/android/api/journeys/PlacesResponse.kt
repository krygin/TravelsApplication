package com.travels.android.api.journeys

import com.squareup.moshi.Json

data class PlacesResponse(
        @Json(name = "status") val status: String,
        @Json(name = "data") val places: List<SuggestPlaceApiModel>
)

