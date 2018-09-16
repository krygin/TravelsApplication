package com.travels.android.design.widget

import java.util.Date

data class RouteItem(val place: Place?, val arrivalDate: Date?, val departureDate: Date?)

data class Place(var title: String?, val location: Location?)

data class Location(val lat: Double, val lng: Double)
