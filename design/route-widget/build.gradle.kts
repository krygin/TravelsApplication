import com.travels.android.build_src.Dependencies


plugins {
    id("com.travels.library")
}

dependencies {
    implementation(Dependencies.kotlinStdLib)
    implementation(Dependencies.appCompatV7)
    implementation(Dependencies.design)
    implementation(Dependencies.rxBindingAppCompatV7Kotlin)
    implementation(Dependencies.rxKotlin)
    implementation(Dependencies.rxRelay2)
}
