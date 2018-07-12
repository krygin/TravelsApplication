import com.android.build.gradle.FeaturePlugin
import com.travels.android.build_src.Versions
import com.travels.android.build_src.plugins.AnyAndroidPlugin
import com.travels.android.build_src.plugins.ApplicationPlugin
import com.travels.android.build_src.util.withType
import com.travels.android.build_src.util.android


// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlinVersion: String by System.getProperties()
    val gradlePluginVersion: String by System.getProperties()
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:$gradlePluginVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://jitpack.io")
        maven("https://maven.google.com")
    }
}

val clean by tasks.creating(Delete::class) {
    delete(rootProject.buildDir)
}

subprojects.withType<AnyAndroidPlugin> { project, plugin ->
    plugins {
        id("kotlin-android")
        id("kotlin-extensions")
    }
    with(project) {
        extensions.android {
            compileSdkVersion(Versions.compileSdkVersion)
            defaultConfig {
                minSdkVersion(21)
                targetSdkVersion(Versions.targetSdkVersion)
                versionCode = 1
                versionName = "1.0"
            }

            buildTypes {
                getByName("release") {
                    setMinifyEnabled(false)
                }
            }
        }
    }
}