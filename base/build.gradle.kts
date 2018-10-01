import com.android.build.api.dsl.extension.FeatureExtension
import com.travels.android.build_src.Dependencies
import com.travels.android.build_src.util.feature

plugins {
    id("com.travels.feature")
    id("kotlin-android")
    id("kotlin-kapt")
}

extensions.feature {
    defaultConfig {
        baseFeature = true

    }
}

dependencies {

    application(project(":app"))
    feature(project(":main"))


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
    api("com.google.code.findbugs:jsr305:3.0.2")

    kapt(Dependencies.androidArchitecturePersistenceRoomCompiler)
    kapt(Dependencies.androidArchitectureLifecycleCompiler)
    kapt(Dependencies.daggerCompiler)

}
