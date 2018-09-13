package com.travels.android.api.journeys

import com.squareup.moshi.Json

data class CreateJourneyResponse(
        @Json(name = "status") val status: String,
        @Json(name = "data") val journey: JourneyApiModel
)