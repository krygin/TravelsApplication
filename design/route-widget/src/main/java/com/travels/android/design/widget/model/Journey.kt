package com.travels.android.design.widget.model

import java.util.Date

data class Journey(var title: String, var itinerary: Itinerary, var description: String)

data class Itinerary(var places: List<PointInfo>)

data class PointInfo(val place: Place?, val arrival: Date?, val departure: Date?)

data class Place(var title: String, val location: Location)

data class Location(val lat: Double, val lng: Double)