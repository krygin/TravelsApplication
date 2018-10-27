import com.travels.android.build_src.Dependencies

plugins {
    id("com.travels.library")
    id("kotlin-kapt")
}


dependencies {
    implementation(project(":base"))
    implementation(project(":design:route-widget"))
    implementation(project(":journeys:domain"))
    implementation(Dependencies.rxRelay2)
    implementation(Dependencies.chipsInputLayout)
    implementation(Dependencies.dagger)
    kapt(Dependencies.daggerCompiler)
}
