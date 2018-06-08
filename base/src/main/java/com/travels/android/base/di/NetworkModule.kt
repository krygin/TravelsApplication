package com.travels.android.base.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun retrofit(): Retrofit {
        val logging = HttpLoggingInterceptor();
        logging.level = HttpLoggingInterceptor.Level.BODY;

        val httpBuilder = OkHttpClient.Builder();
        httpBuilder.addInterceptor(logging)

        return Retrofit.Builder()
                .baseUrl("http://travels.dev.ktsstudio.ru")
                .client(httpBuilder.build())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}