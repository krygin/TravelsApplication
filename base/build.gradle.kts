import com.android.build.gradle.FeatureExtension
import com.travels.android.build_src.Dependencies

plugins {
    id("com.travels.library")
    id("kotlin-kapt")
}

dependencies {

    implementation(project(":data"))

    api(Dependencies.moshiKotlin)
    api(Dependencies.retrofitMoshiConverter)
    api(Dependencies.okHttpLoggingInterceptor)
    api(Dependencies.retrofitRxJavaAdapter)
    api(Dependencies.retrofit)
    api(Dependencies.androidArchitectureLifecycleExtensions)
    api(Dependencies.androidArchitectureLifecycleReactiveStreams)
    api(Dependencies.androidArchitecturePersistenceRoomRuntime)
    api(Dependencies.rxJava)
    api(Dependencies.rxAndroid)
    api(Dependencies.rxKotlin)
    api(Dependencies.rxBindingAppCompatV7Kotlin)
    api(Dependencies.appCompatV7)
    api(Dependencies.kotlinStdLib)
    api(Dependencies.androidArchitecturePresistenceRoomRxJava)
    api(Dependencies.design)
    api(Dependencies.dagger)

    kapt(Dependencies.androidArchitecturePersistenceRoomCompiler)
    kapt(Dependencies.androidArchitectureLifecycleCompiler)
    kapt(Dependencies.daggerCompiler)

}
