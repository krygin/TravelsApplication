package com.travels.android.build_src

object Versions {
    const val compileSdkVersion = 28
    const val minSdkVersion = 21
    const val targetSdkVersion = 28
}

object Dependencies {

    @Suppress("MemberVisibilityCanBePrivate")
    val allProjectDependencies: MutableList<String> = mutableListOf()

    private fun dependency(dependency: String): String {
        allProjectDependencies.add(dependency.replace("@.+".toRegex(), ""))
        return dependency
    }



    private val kotlinVersion = System.getProperty("kotlinVersion")
    private val androidPluginVersion = System.getProperty("androidPluginVersion")
    private const val supportLibraryVersion = "28.0.0-rc02"
    private const val googlePlayServicesVersion = "16.0.2"
    private const val androidArchitectureLifecycleVersion = "1.1.1"
    private const val daggerVersion = "2.15"
    private const val rxBindingVersion = "2.1.1"
    private const val navigationVersion = "1.0.0-alpha06"

    @JvmStatic val kotlinGradlePlugin = dependency("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    @JvmStatic val androidPlugin = dependency("com.android.tools.build:gradle:$androidPluginVersion")
    @JvmStatic val kotlinStdLib = dependency("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    @JvmStatic val kotlinStdlibCommon = dependency("org.jetbrains.kotlin:kotlin-stdlib-common:$kotlinVersion")
    @JvmStatic val kotlinReflect = dependency("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

    @JvmStatic val appCompatV7 = dependency("com.android.support:appcompat-v7:$supportLibraryVersion")
    @JvmStatic val design = dependency("com.android.support:design:$supportLibraryVersion")
    @JvmStatic val supportV4 = dependency("com.android.support:support-v4:$supportLibraryVersion")
    @JvmStatic val preferenceV7 = dependency("com.android.support:preference-v7:$supportLibraryVersion")
    @JvmStatic val customTabs = dependency("com.android.support:customtabs:$supportLibraryVersion")
    @JvmStatic val constraintLayout = dependency("com.android.support.constraint:constraint-layout:1.1.2")

    @JvmStatic val chipsInputLayout = dependency("com.github.tylersuehr7:chips-input-layout:2.3")

    @JvmStatic val rxJava = dependency("io.reactivex.rxjava2:rxjava:2.2.0")
    @JvmStatic val rxKotlin = dependency("io.reactivex.rxjava2:rxkotlin:2.3.0")
    @JvmStatic val rxAndroid = dependency("io.reactivex.rxjava2:rxandroid:2.1.0")

    @JvmStatic val androidArchitectureLifecycleExtensions = dependency("android.arch.lifecycle:extensions:$androidArchitectureLifecycleVersion")
    @JvmStatic val androidArchitectureLifecycleViewModel = dependency("android.arch.lifecycle:viewmodel:$androidArchitectureLifecycleVersion")
    @JvmStatic val androidArchitectureLifecycleLiveData = dependency("android.arch.lifecycle:livedata:$androidArchitectureLifecycleVersion")
    @JvmStatic val androidArchitectureLifecycleReactiveStreams = dependency("android.arch.lifecycle:reactivestreams:$androidArchitectureLifecycleVersion")
    @JvmStatic val androidArchitectureLifecycleCompiler = dependency("android.arch.lifecycle:compiler:$androidArchitectureLifecycleVersion")

    @JvmStatic val androidArchitecturePersistenceRoomRuntime = dependency("android.arch.persistence.room:runtime:1.1.1")
    @JvmStatic val androidArchitecturePersistenceRoomCompiler = dependency("android.arch.persistence.room:compiler:1.1.1")
    @JvmStatic val androidArchitecturePresistenceRoomRxJava = dependency("android.arch.persistence.room:rxjava2:1.1.1")

    @JvmStatic val androidArchitectureNavigationFragment = dependency("android.arch.navigation:navigation-fragment-ktx:$navigationVersion")
    @JvmStatic val androidArchitectureNavigationUI = dependency("android.arch.navigation:navigation-ui-ktx:$navigationVersion")

    @JvmStatic val googlePlayServicesMaps = dependency("com.google.android.gms:play-services-maps:15.0.1")
    @JvmStatic val googlePlayServicesPlaces = dependency("com.google.android.gms:play-services-places:15.0.1")

    @JvmStatic val retrofit = dependency("com.squareup.retrofit2:retrofit:2.4.0")
    @JvmStatic val retrofitRxJavaAdapter = dependency("com.squareup.retrofit2:adapter-rxjava2:2.4.0")
    @JvmStatic val moshi = dependency("com.squareup.moshi:moshi:1.7.0")
    @JvmStatic val moshiKotlin = dependency("com.squareup.moshi:moshi-kotlin:1.7.0")
    @JvmStatic val okHttpLoggingInterceptor = dependency("com.squareup.okhttp3:logging-interceptor:3.8.0")
    @JvmStatic val retrofitMoshiConverter = dependency("com.squareup.retrofit2:converter-moshi:2.4.0")

    @JvmStatic val okio = dependency("com.squareup.okio:okio:${"okioVersion".toProperty()}")

    @JvmStatic val dagger = dependency("com.google.dagger:dagger:$daggerVersion")
    @JvmStatic val daggerAndroid = dependency("com.google.dagger:dagger-android:$daggerVersion")
    @JvmStatic val daggerAndroidSupport = dependency("com.google.dagger:dagger-android-support:$daggerVersion")
    @JvmStatic val daggerCompiler = dependency("com.google.dagger:dagger-compiler:$daggerVersion")
    @JvmStatic val daggerAndroidCompiler = dependency("com.google.dagger:dagger-android-processor:$daggerVersion")


    @JvmStatic val rxBindingKotlin = dependency("com.jakewharton.rxbinding2:rxbinding-kotlin:$rxBindingVersion")
    @JvmStatic val rxBindingSupportV4Kotlin = dependency("com.jakewharton.rxbinding2:rxbinding-support-v4-kotlin:$rxBindingVersion")
    @JvmStatic val rxBindingAppCompatV7Kotlin = dependency("com.jakewharton.rxbinding2:rxbinding-appcompat-v7-kotlin:$rxBindingVersion")
    @JvmStatic val rxBinding = dependency("com.jakewharton.rxbinding2:rxbinding:$rxBindingVersion")
    @JvmStatic val rxBindingSupportV4 = dependency("com.jakewharton.rxbinding2:rxbinding-support-v4:$rxBindingVersion")
    @JvmStatic val rxBindingAppCompatV7 = dependency("com.jakewharton.rxbinding2:rxbinding-appcompat-v7:$rxBindingVersion")
    @JvmStatic val rxRelay2 = dependency("com.jakewharton.rxrelay2:rxrelay:2.0.0")

}

private fun String.toProperty(): String = System.getProperty(this)