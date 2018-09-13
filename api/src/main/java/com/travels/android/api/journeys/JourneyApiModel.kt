package com.travels.android.api.journeys

import com.squareup.moshi.Json
import java.util.*

data class JourneyApiModel(
        @Json(name = "id") val id: String,
        @Json(name = "title") val title: String,
        @Json(name = "description") val description: String,
        @Json(name = "route") val route: List<RouteItemApiModel>
)

data class RouteItemApiModel(
        @Json(name = "id") val id: String,
        @Json(name = "arrival") val arrival: Date,
        @Json(name = "departure") val departure: Date,
        @Json(name = "place") val place: PlaceApiModel
)

data class PlaceApiModel(
        @Json(name = "id") val id: String,
        @Json(name = "title") val title: String,
        @Json(name = "location") val location: LocationApiModel
)

data class LocationApiModel(
        @Json(name = "lat") val lat: Double,
        @Json(name = "lng") val lng: Double
)