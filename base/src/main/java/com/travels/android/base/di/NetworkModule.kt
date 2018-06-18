package com.travels.android.base.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun retrofit(): Retrofit {
        val logging = HttpLoggingInterceptor();
        logging.level = HttpLoggingInterceptor.Level.BODY;

        val httpBuilder = OkHttpClient.Builder();

        val trustAllCerts = arrayOf(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> = emptyArray()

        })

        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        val sslSocketFactory = sslContext.socketFactory
        httpBuilder.addInterceptor(logging)
        httpBuilder.sslSocketFactory(sslSocketFactory, trustAllCerts[0])
        httpBuilder.hostnameVerifier { hostname, session -> true }
        return Retrofit.Builder()
                .baseUrl("https://travels.dev.ktsstudio.ru")
                .client(httpBuilder.build())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}