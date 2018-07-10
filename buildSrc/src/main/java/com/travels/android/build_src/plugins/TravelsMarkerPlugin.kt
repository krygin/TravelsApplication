package com.travels.android.build_src.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

abstract class TravelsMarkerPlugin: Plugin<Project>

abstract class AnyAndroidPlugin: TravelsMarkerPlugin()

class ApplicationPlugin: AnyAndroidPlugin() {
    override fun apply(project: Project) {
    }
}

class AndroidLibraryPlugin: AnyAndroidPlugin() {
    override fun apply(project: Project) {
    }
}

class AndroidFeaturePlugin: AnyAndroidPlugin() {
    override fun apply(target: Project) {
    }
}

class KotlinLibraryPlugin: TravelsMarkerPlugin() {
    override fun apply(project: Project) {
    }
}