package com.travels.android.api.journeys


fun Journey.toJourneyApiModel() = JourneyApiModel(id, title, description, route.toRouteApiModelList())

fun List<RouteItem>.toRouteApiModelList() = map { it.toRouteApiModel() }

fun RouteItem.toRouteApiModel() = RouteItemApiModel(id, arrival, departure, place.toPlaceApiModel())

fun Place.toPlaceApiModel() = PlaceApiModel(id, title, location.toLocationApiModel())

fun Location.toLocationApiModel() = LocationApiModel(lat, lng)


fun JourneyApiModel.toJourney() = Journey(id, title, description, route.toRouteList())

fun List<RouteItemApiModel>.toRouteList() = map { it.toRoute() }

fun RouteItemApiModel.toRoute() = RouteItem(id, arrival, departure, place.toPlace())

fun PlaceApiModel.toPlace() = Place(id, title, location.toLocation())

fun LocationApiModel.toLocation() = Location(lat, lng)


fun Journey.toJourneyModel() = JourneyModel(id = id, title = title, description = description)

fun RouteItem.toRouteModel(journeyId: Long) = RouteItemModel(id = id, journeyId = journeyId, arrival = arrival, departure = departure, place = place.toPlaceModel())

fun Place.toPlaceModel() = PlaceModel(id = id, title = title, lat = location.lat, lng = location.lng)


fun JourneyModel.toJourney() = Journey(id, title, description, emptyList())

fun RouteItemModel.toRoute() = RouteItem(id, arrival, departure, place.toPlace())

fun PlaceModel.toPlace() = Place(id, title, Location(lat, lng))