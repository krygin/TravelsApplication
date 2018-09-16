package com.travels.android.api.journeys

import java.util.Date

data class Journey(var id: String, var title: String, var description: String, var route: List<RouteItem>)

data class RouteItem(val id: String, val arrival: Date, val departure: Date, val place: Place)

data class Place(val id: String, val title: String, val location: Location)

data class Location(val lat: Double, val lng: Double)