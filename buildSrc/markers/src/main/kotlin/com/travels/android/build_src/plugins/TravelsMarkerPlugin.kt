package com.travels.android.build_src.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.jetbrains.kotlin.js.translate.context.Namer.kotlin

abstract class TravelsMarkerPlugin : Plugin<Project>

abstract class AnyAndroidPlugin : TravelsMarkerPlugin()

class ApplicationPlugin : AnyAndroidPlugin() {
    override fun apply(project: Project) {
        project.apply {
            plugin("com.android.application")
            plugin("kotlin-android")
        }
    }
}

class AndroidLibraryPlugin : AnyAndroidPlugin() {
    override fun apply(project: Project) {
        project.apply {
            plugin("com.android.library")
            plugin("kotlin-android")
        }
    }
}

class AndroidFeaturePlugin : AnyAndroidPlugin() {
    override fun apply(project: Project) {
        project.apply {
            plugin("com.android.feature")
            plugin("kotlin-android")
        }

    }
}

class KotlinLibraryPlugin : TravelsMarkerPlugin() {
    override fun apply(project: Project) {
        project.apply {
            plugin("kotlin")
        }
    }
}