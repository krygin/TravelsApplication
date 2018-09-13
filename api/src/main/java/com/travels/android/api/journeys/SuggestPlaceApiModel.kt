package com.travels.android.api.journeys

import com.squareup.moshi.Json

data class SuggestPlaceApiModel(
        @Json(name = "place_id") val googlePlaceId: String,
        @Json(name = "id") val id: String,
        @Json(name = "inputs") val inputs: List<String>,
        @Json(name = "description") val description: String
)