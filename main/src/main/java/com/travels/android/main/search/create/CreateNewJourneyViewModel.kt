package com.travels.android.main.search.create

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.travels.android.main.search.core.*
import java.util.*

class CreateNewJourneyViewModel : ViewModel() {

    val journey: MutableLiveData<Journey> = MutableLiveData()

    val itiniary: LiveData<Itinerary> = Transformations.map(journey, { it.itinerary })

    init {
        journey.value = Journey(
                "Original Title",
                Itinerary(createDummyPointInfoList()),
                "Original description")
    }

    fun updateJourney(updatedJourney: Journey) {
        journey.value = updatedJourney
    }

}

private fun createDummyPointInfoList(): List<PointInfo> {
    return listOf(
            PointInfo(Place(Location(55.45, 37.37), "Москва"), Date(), Date()),
            PointInfo(Place(Location(52.31, 13.23), "Берлин"), Date(), Date()),
            PointInfo(Place(Location(48.50, 2.20), "Париж"), Date(), Date()),
            PointInfo(Place(Location(41.00, 28.57), "Стамбул"), Date(), Date())
    )
}