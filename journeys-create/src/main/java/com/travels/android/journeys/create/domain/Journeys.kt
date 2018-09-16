package com.travels.android.journeys.create.domain

import com.travels.android.journeys.domain.Location
import com.travels.android.journeys.domain.Place

fun Place.toPlace() = com.travels.android.design.widget.model.Place(title, location.toLocation())

fun Location.toLocation() = com.travels.android.design.widget.model.Location(lat, lng)