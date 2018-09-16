import com.travels.android.build_src.Dependencies


plugins {
    id("com.travels.kotlin")
    id("kotlin-kapt")
}

dependencies {
    implementation(Dependencies.kotlinStdLib)
    implementation(Dependencies.rxKotlin)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitRxJavaAdapter)
    implementation(Dependencies.moshiKotlin)
    implementation(Dependencies.androidArchitecturePersistenceRoomRuntime)
    kapt(Dependencies.androidArchitecturePersistenceRoomCompiler)
}
