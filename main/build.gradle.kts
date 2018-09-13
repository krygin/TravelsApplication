import com.travels.android.build_src.Dependencies

plugins {
    id("com.travels.feature")
    id("kotlin-kapt")
}


dependencies {
    implementation(Dependencies.kotlinStdLib)
    implementation(project(":base"))
    implementation(project(":api"))
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

    implementation("com.android.support:design:28.0.0-alpha3")
    implementation("com.android.support:support-v4:27.0.2")


}
