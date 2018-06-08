package com.travels.android.auth.di

import com.travels.android.auth.AuthFragment
import com.travels.android.base.di.BaseInjector
import com.travels.android.base.di.BaseSubComponent
import com.travels.android.base.di.PerActivity
import dagger.Component

@PerActivity
@Component(
        dependencies = [BaseSubComponent::class],
        modules = [AuthModule::class]
)
interface AuthComponent: BaseInjector<AuthFragment> {

    @Component.Builder
    abstract class Builder : BaseInjector.Builder<AuthFragment>()

}