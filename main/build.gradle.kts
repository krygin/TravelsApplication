import com.travels.android.build_src.Dependencies

plugins {
    id("com.travels.library")
    id("kotlin-kapt")
}


dependencies {
    implementation(Dependencies.kotlinStdLib)
    api(project(":main-search"))
    implementation(project(":base"))
    implementation(project(":data"))
    implementation(Dependencies.androidArchitectureNavigationUI)
    implementation(Dependencies.androidArchitectureNavigationFragment)
    implementation(Dependencies.googlePlayServicesMaps)
    implementation(Dependencies.googlePlayServicesPlaces)
    implementation(Dependencies.rxBindingKotlin)
    implementation(Dependencies.rxBindingAppCompatV7Kotlin)
    implementation(Dependencies.rxBindingSupportV4Kotlin)
    implementation(Dependencies.rxRelay2)
    implementation(Dependencies.chipsInputLayout)
    implementation(project(":design:date-range-picker"))
    implementation(project(":design:route-widget"))

    kapt(Dependencies.daggerCompiler)


}
