package com.travels.android.design.widget

import com.travels.android.design.widget.model.Place
import io.reactivex.Observable

interface PlaceListProvider {
    fun getPlacesObservable(): Observable<List<Place>>
}