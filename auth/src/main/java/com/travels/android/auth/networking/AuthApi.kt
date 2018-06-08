package com.travels.android.auth.networking

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthApi {
    @GET("/sync/api/app/vk-auth-code-flow/")
    fun loginVK(@Query("code") code: String, @Query("redirect_uri") redirectUri: String = "http://oauth_callback"): Observable<VKOAuthResponse>
}