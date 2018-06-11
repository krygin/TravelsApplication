package com.travels.android.main.search.core

import java.util.Date

data class Journey(var title: String, var itinerary: Itinerary, var description: String)

data class Itinerary(val places: List<PointInfo>)

data class PointInfo(val place: Place, val arrival: Date, val departure: Date)

data class Place(val location: Location, var name: String)

data class Location(val lat: Double, val lng: Double)