import com.travels.android.build_src.Dependencies

plugins {
    id("com.travels.feature")
    id("kotlin-kapt")
}


dependencies {
    implementation(Dependencies.kotlinStdLib)
    implementation(project(":main-search"))
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
    implementation("com.github.tylersuehr7:chips-input-layout:2.3")
    implementation(project(":design:date-range-picker"))
    implementation(project(":design:route-widget"))

    kapt(Dependencies.daggerCompiler)


}
