package com.travels.android.auth.networking

import com.squareup.moshi.Json

data class VKOAuthResponse(
        @Json(name = "status") val status: String,
        @Json(name = "data") val vkProfile: VKProfile
)

data class VKProfile(
        @Json(name = "last_name") val lastName: String,
        @Json(name = "first_name") val firstName: String,
        @Json(name = "profile_image") val profileImage: String,
        @Json(name = "is_superuser") val isSuperuser: Int,
        @Json(name = "id") val id: Long
)