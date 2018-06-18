package com.travels.android.main.search.create.di

import com.travels.android.base.di.PerActivity
import com.travels.android.main.search.create.CreateNewJourneyViewModel
import com.travels.android.main.search.create.domain.GetSuggestedPlacesUseCase
import com.travels.android.main.search.create.domain.PlacesRepository
import com.travels.android.main.search.create.domain.PlacesRepositoryImpl
import com.travels.android.main.search.create.networking.PlacesApi
import com.travels.android.main.search.create.util.CreateNewJourneyViewModelFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CreateNewJourneyModule {

    @Provides
    @PerActivity
    fun providerPlacesApi(retrofit: Retrofit): PlacesApi {
        return retrofit.create(PlacesApi::class.java)
    }

    @Provides
    @PerActivity
    fun providePlacesRepository(placesApi: PlacesApi): PlacesRepository {
        return PlacesRepositoryImpl(placesApi)
    }

    @Provides
    @PerActivity
    fun provideSuggestedPlacesUseCase(placesRepository: PlacesRepository): GetSuggestedPlacesUseCase {
        return GetSuggestedPlacesUseCase(placesRepository)
    }

    @Provides
    @PerActivity
    fun provideCreateNewJourneyViewModelFactory(getSuggestedPlacesUseCase: GetSuggestedPlacesUseCase): CreateNewJourneyViewModelFactory {
        return CreateNewJourneyViewModelFactory(getSuggestedPlacesUseCase)
    }

}