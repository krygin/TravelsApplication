package com.travels.android.journeys.create.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.travels.android.journeys.create.CreateNewJourneyViewModel
import com.travels.android.journeys.domain.GetSuggestedPlacesUseCase
import com.travels.android.journeys.domain.SaveJourneyUseCase

class CreateNewJourneyViewModelFactory(
        private val getSuggestedPlacesUseCase: GetSuggestedPlacesUseCase,
        private val saveJourneyUseCase: SaveJourneyUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(CreateNewJourneyViewModel::class.java) -> CreateNewJourneyViewModel(
                        getSuggestedPlacesUseCase,
                        saveJourneyUseCase
                )
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
    }
}