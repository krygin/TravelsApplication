package com.travels.android.build_src.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

abstract class TravelsMarkerPlugin: Plugin<Project>

abstract class AnyAndroidPlugin: TravelsMarkerPlugin()

class ApplicationPlugin: AnyAndroidPlugin() {
    override fun apply(project: Project) {
        project.apply(plugin = "com.android.application")
    }
}

class AndroidLibraryPlugin: AnyAndroidPlugin() {
    override fun apply(project: Project) {
        project.apply(plugin = "com.android.library")
    }
}

class AndroidFeaturePlugin: AnyAndroidPlugin() {
    override fun apply(project: Project) {
        project.apply(plugin = "com.android.feature")
    }
}

class KotlinLibraryPlugin: TravelsMarkerPlugin() {
    override fun apply(project: Project) {
        project.apply(plugin="java-library")
        project.apply(plugin="kotlin")
    }
}