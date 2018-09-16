import com.travels.android.build_src.Dependencies

plugins {
    id("com.travels.library")
    id("kotlin-kapt")
}


dependencies {
    implementation(project(":base"))
    implementation(project(":journeys:domain"))
    implementation(Dependencies.androidArchitectureNavigationUI)
    implementation(Dependencies.androidArchitectureNavigationFragment)
    implementation(Dependencies.dagger)
    implementation(Dependencies.googlePlayServicesMaps)
    kapt(Dependencies.daggerCompiler)
}
