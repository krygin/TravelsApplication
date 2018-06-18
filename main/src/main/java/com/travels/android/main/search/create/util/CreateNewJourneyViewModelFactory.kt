package com.travels.android.main.search.create.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.travels.android.main.search.create.CreateNewJourneyViewModel
import com.travels.android.main.search.create.domain.GetSuggestedPlacesUseCase

class CreateNewJourneyViewModelFactory(val getSuggestedPlacesUseCase: GetSuggestedPlacesUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(CreateNewJourneyViewModel::class.java) -> CreateNewJourneyViewModel(getSuggestedPlacesUseCase)
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
    }
}