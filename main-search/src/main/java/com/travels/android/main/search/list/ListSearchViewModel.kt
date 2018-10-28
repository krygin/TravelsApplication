package com.travels.android.main.search.list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import com.travels.android.api.journeys.Journey
import com.travels.android.base.domain.Response
import com.travels.android.journeys.domain.GetJourneysUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class ListSearchViewModel(private val getJourneysUseCase: GetJourneysUseCase) : ViewModel() {

    private val disposables = CompositeDisposable()


    val journeys = MutableLiveData<Response<List<Journey>>>()


    fun getJourneys() {
        disposables += getJourneysUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { journeys.value = Response.Success(it) },
                        { journeys.value = Response.Failure(it) }
                )
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}
