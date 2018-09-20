package com.travels.android.api.journeys

import io.reactivex.Single

class PlacesRepositoryImpl(private val journeysApi: JourneysApi, private val journeyDao: JourneyDAO) : PlacesRepository {

    override fun getPlaces(query: String): Single<List<Place>> {
        return journeysApi
                .getPlaces(query)
                .map { placesResponse -> placesResponse.places.map { Place("", it.description, Location(0.0, 0.0)) } }
                .singleOrError()
    }

    override fun saveJourney(journey: Journey): Single<Journey> {
        return journeyDao.saveJourney(journey.toJourneyModel())
                .flatMap { journeyId ->
                    journeyDao.saveRoutes(*journey.route.map { routeItem -> routeItem.toRouteModel() }.toTypedArray())
                            .map { journeyId }
                }
                .flatMap {
                    journeyDao.getJourney(it).map {
                        journeyModel -> journeyModel.toJourney() }
                }
    }

    override fun getJourneys(): Single<List<Journey>> {
        return journeyDao.allJourneys()
                .map { it.map { journeyModel -> journeyModel.toJourney() } }
    }

}