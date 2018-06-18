package com.travels.android.main.search.core.widget

import com.travels.android.main.search.core.Place
import io.reactivex.Observable

interface PlaceListProvider {
    fun getPlacesObservable(): Observable<List<Place>>
}