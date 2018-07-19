package com.travels.android.app.di

import com.travels.android.app.TravelsApplication
import com.travels.android.auth.di.AuthDependencies
import com.travels.android.base.di.ComponentDependencies
import com.travels.android.base.di.ComponentDependenciesKey
import com.travels.android.base.di.NetworkModule
import com.travels.android.base.di.PersistenceModule
import com.travels.android.main.search.create.di.CreateNewJourneyDependencies
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.multibindings.IntoMap
import javax.inject.Scope
import javax.inject.Singleton


@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class MainActivityScope

@Singleton
@Component(
        modules = [ApplicationModule::class, NetworkModule::class, PersistenceModule::class, ComponentDependenciesModule::class]
)
internal interface ApplicationComponent :
        AuthDependencies,
        CreateNewJourneyDependencies {
    fun inject(application: TravelsApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun create(application: TravelsApplication): Builder

        fun build(): ApplicationComponent
    }

}

@dagger.Module
private abstract class ComponentDependenciesModule private constructor() {
    @Binds
    @IntoMap
    @ComponentDependenciesKey(AuthDependencies::class)
    abstract fun provideAuthDependencies(component: ApplicationComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(CreateNewJourneyDependencies::class)
    abstract fun provideCreateNewJourneyDependencies(component: ApplicationComponent): ComponentDependencies
}