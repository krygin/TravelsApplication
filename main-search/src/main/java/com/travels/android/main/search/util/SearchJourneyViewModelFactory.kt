package com.travels.android.main.search.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.travels.android.journeys.domain.GetJourneysUseCase
import com.travels.android.main.search.list.ListSearchViewModel

class SearchJourneyViewModelFactory(private val getJourneysUseCase: GetJourneysUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(ListSearchViewModel::class.java) -> ListSearchViewModel(
                        getJourneysUseCase
                )
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
    }
}