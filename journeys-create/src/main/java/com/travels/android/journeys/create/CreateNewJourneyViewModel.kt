package com.travels.android.journeys.create

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.travels.android.api.journeys.Journey
import com.travels.android.api.journeys.Location
import com.travels.android.api.journeys.Place
import com.travels.android.api.journeys.RouteItem
import com.travels.android.base.domain.Response
import com.travels.android.journeys.domain.GetSuggestedPlacesUseCase
import com.travels.android.journeys.domain.SaveJourneyUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import java.util.*

class CreateNewJourneyViewModel(
        private val getSuggestedPlacesUseCase: GetSuggestedPlacesUseCase,
        private val saveJourneyUseCase: SaveJourneyUseCase
) : ViewModel() {

    val journey: MutableLiveData<Journey> = MutableLiveData()

    private val disposables = CompositeDisposable()

    val route: LiveData<List<RouteItem>> = Transformations.map(this.journey, { it.route })

    val places: MutableLiveData<Response<List<Place>>> = MutableLiveData()

    init {
        this.journey.value = Journey(
                "qwe",
                "Original Title",
                "Original description",
                createDummyPointInfoList())
    }

    fun updateJourney(updatedJourney: Journey) {
        this.journey.value = updatedJourney
    }

    fun updateJourneyRoutes(route: List<RouteItem>) {
        journey.value = journey.value?.copy(route = route)
    }

    fun getPlaces(query: String) {
        disposables += getSuggestedPlacesUseCase.execute(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { places.value = Response.Loading() }
                .subscribe(
                        { it -> places.value = Response.Success(it) },
                        { it -> places.value = Response.Failure(it) }
                )
    }

    fun saveJourney(journey: Journey) {
        disposables += saveJourneyUseCase.execute(journey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e("TAG", "SUCESS")
                }, { Log.e("TAG", "FAIL", it) })
    }

}

private fun createDummyPointInfoList(): List<RouteItem> {
    return listOf(
            RouteItem("", Date(), Date(), Place("", "Москва", Location(55.45, 37.37))),
            RouteItem("", Date(), Date(), Place("", "Берлин", Location(52.31, 13.23))),
            RouteItem("", Date(), Date(), Place("", "Париж", Location(48.50, 2.20))),
            RouteItem("", Date(), Date(), Place("", "Стамбул", Location(41.00, 28.57)))
    )
}

