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
        return Single.fromCallable {
            val journeyId = journeyDao.saveJourney(journey.toJourneyModel())
            val routeIds = journeyDao.saveRoutes(*journey.route.map { routeItem -> routeItem.toRouteModel() }.toTypedArray())
            val routes = journeyDao.getRoutes(routeIds)
            val journeyModel = journeyDao.getJourney(journeyId)
            return@fromCallable journeyModel.toJourney().apply { route = routes.map { it.toRoute() } }
        }
    }

    override fun getJourneys(): Single<List<Journey>> {
        return journeyDao.allJourneys()
                .map { it.map { journeyModel -> journeyModel.toJourney() } }
    }

}