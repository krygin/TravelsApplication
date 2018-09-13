import com.travels.android.build_src.Dependencies


plugins {
    id("com.travels.kotlin")
}

dependencies {
    implementation(Dependencies.rxKotlin)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitRxJavaAdapter)
    implementation(Dependencies.moshiKotlin)
}
