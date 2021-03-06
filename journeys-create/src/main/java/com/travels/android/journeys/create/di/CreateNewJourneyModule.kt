package com.travels.android.journeys.create.di

import com.travels.android.api.journeys.JourneyDAO
import com.travels.android.api.journeys.JourneysApi
import com.travels.android.base.di.PerActivity
import com.travels.android.base.persistence.TravelsDatabase
import com.travels.android.journeys.domain.GetSuggestedPlacesUseCase
import com.travels.android.api.journeys.PlacesRepositoryImpl
import com.travels.android.journeys.create.util.CreateNewJourneyViewModelFactory
import com.travels.android.api.journeys.PlacesRepository
import com.travels.android.journeys.domain.SaveJourneyUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CreateNewJourneyModule {

    @Provides
    @PerActivity
    fun providerPlacesApi(retrofit: Retrofit): JourneysApi {
        return retrofit.create(JourneysApi::class.java)
    }

    @Provides
    @PerActivity
    fun journeysDao(travelsDatabase: TravelsDatabase): JourneyDAO {
        return travelsDatabase.journeyDao()
    }

    @Provides
    @PerActivity
    fun providePlacesRepository(journeysApi: JourneysApi, journeyDAO: JourneyDAO): PlacesRepository {
        return PlacesRepositoryImpl(journeysApi, journeyDAO)
    }

    @Provides
    @PerActivity
    fun provideSuggestedPlacesUseCase(placesRepository: PlacesRepository): GetSuggestedPlacesUseCase {
        return GetSuggestedPlacesUseCase(placesRepository)
    }

    @Provides
    @PerActivity
    fun provideSaveJourneyUseCase(placesRepository: PlacesRepository): SaveJourneyUseCase {
        return SaveJourneyUseCase(placesRepository)
    }

    @Provides
    @PerActivity
    fun provideCreateNewJourneyViewModelFactory(getSuggestedPlacesUseCase: GetSuggestedPlacesUseCase, saveJourneyUseCase: SaveJourneyUseCase): CreateNewJourneyViewModelFactory {
        return CreateNewJourneyViewModelFactory(getSuggestedPlacesUseCase, saveJourneyUseCase)
    }

}