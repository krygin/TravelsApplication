package com.travels.android.auth.di

import com.travels.android.auth.AuthFragment
import com.travels.android.base.di.PerActivity
import dagger.Component

@PerActivity
@Component(
        dependencies = [AuthDependencies::class],
        modules = [AuthModule::class]
)
interface AuthComponent {

    fun inject(authFragment: AuthFragment)

}