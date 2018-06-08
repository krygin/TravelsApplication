package com.travels.android.auth.di

import android.content.Context
import com.travels.android.auth.AuthViewModelFactory
import com.travels.android.auth.domain.*
import com.travels.android.auth.networking.AuthApi
import com.travels.android.base.di.PerActivity
import com.travels.android.base.persistence.TravelsDatabase
import com.travels.android.base.persistence.user.UserDAO
import com.travels.android.base.persistence.user.UserRepository
import com.travels.android.base.persistence.user.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {

    @Provides
    @PerActivity
    fun provideAuthViewModelFactory(loginVKUseCase: LoginVKUseCase): AuthViewModelFactory {
        return AuthViewModelFactory(loginVKUseCase)
    }

    @Provides
    @PerActivity
    fun provideLoginVKUseCase(userRepository: UserRepository, authRepository: AuthRepository, accountManager: AccountManager): LoginVKUseCase {
        return LoginVKUseCase(userRepository, authRepository, accountManager)
    }

    @Provides
    @PerActivity
    fun provideUserRepository(userDAO: UserDAO): UserRepository {
        return UserRepositoryImpl(userDAO)
    }

    @Provides
    @PerActivity
    fun provideAuthRepository(authApi: AuthApi): AuthRepository {
        return AuthRepositoryImpl(authApi)
    }

    @Provides
    @PerActivity
    fun provideAccountManager(accountManager: android.accounts.AccountManager): AccountManager {
        return AccountManagerImpl(accountManager)
    }

    @Provides
    @PerActivity
    fun provideUserDao(travelsDatabase: TravelsDatabase): UserDAO {
        return travelsDatabase.userDao()
    }


    @Provides
    @PerActivity
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @PerActivity
    fun provideSystemAccountManager(context: Context): android.accounts.AccountManager {
        return android.accounts.AccountManager.get(context)
    }

}