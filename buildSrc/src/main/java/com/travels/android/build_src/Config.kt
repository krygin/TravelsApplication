package com.travels.android.build_src

object Versions {
    const val compileSdkVersion = 27
    const val minSdkVersion = 21
    const val targetSdkVersion = 27
}

object Dependencies {
    private const val kotlinVersion = "1.2.41"
    private const val androidPluginVersion = "3.2.0-alpha16"
    private const val supportLibraryVersion = "27.1.1"
    private const val googlePlayServicesVersion = "12.0.0"
    private const val androidArchitectureLifecycleVersion = "1.1.1"
    private const val daggerVersion = "2.15"
    private const val rxBindingVersion = "2.1.1"

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val androidPlugin = "com.android.tools.build:gradle:$androidPluginVersion"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"

    const val appCompatV7 = "com.android.support:appcompat-v7:$supportLibraryVersion"
    const val design = "com.android.support:design:$supportLibraryVersion"
    const val supportV4 = "com.android.support:support-v4:$supportLibraryVersion"
    const val preferenceV7 = "com.android.support:preference-v7:$supportLibraryVersion"
    const val customTabs = "com.android.support:customtabs:$supportLibraryVersion"

    const val rxJava = "io.reactivex.rxjava2:rxjava:2.1.9"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.0.2"

    const val androidArchitectureLifecycleExtensions = "android.arch.lifecycle:extensions:$androidArchitectureLifecycleVersion"
    const val androidArchitectureLifecycleViewModel = "android.arch.lifecycle:viewmodel:$androidArchitectureLifecycleVersion"
    const val androidArchitectureLifecycleLiveData = "android.arch.lifecycle:livedata:$androidArchitectureLifecycleVersion"
    const val androidArchitectureLifecycleCompiler = "android.arch.lifecycle:compiler:$androidArchitectureLifecycleVersion"

    const val androidArchitecturePersistenceRoomRuntime = "android.arch.persistence.room:runtime:1.1.1-rc1"
    const val androidArchitecturePersistenceRoomCompiler = "android.arch.persistence.room:compiler:1.1.1-rc1"

    const val googlePlayServicesMaps = "com.google.android.gms:play-services-maps:$googlePlayServicesVersion"
    const val googlePlayServicesPlaces = "com.google.android.gms:play-services-places:$googlePlayServicesVersion"

    const val retrofit = "com.squareup.retrofit2:retrofit:2.3.0"
    const val retrofitRxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:2.3.0"
    const val moshi = "com.squareup.moshi:moshi:1.6.0"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:1.6.0"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:3.8.0"
    const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:2.4.0"


    const val dagger = "com.google.dagger:dagger:$daggerVersion"
    const val daggerAndroid = "com.google.dagger:dagger-android:$daggerVersion"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:$daggerVersion"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    const val daggerAndroidCompiler = "com.google.dagger:dagger-android-processor:$daggerVersion"


    const val rxBindingKotlin = "com.jakewharton.rxbinding2:rxbinding-kotlin:$rxBindingVersion"
    const val rxBindingSupportV4Kotlin = "com.jakewharton.rxbinding2:rxbinding-support-v4-kotlin:$rxBindingVersion"
    const val rxBindingAppCompatV7Kotlin = "com.jakewharton.rxbinding2:rxbinding-appcompat-v7-kotlin:$rxBindingVersion"

}