package com.travels.android.design.widget

import io.reactivex.Observable

interface PlaceListProvider {
    fun getPlacesObservable(): Observable<List<Place>>
}