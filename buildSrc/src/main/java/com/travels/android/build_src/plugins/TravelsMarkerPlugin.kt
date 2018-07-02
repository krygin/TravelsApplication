package com.travels.android.build_src.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

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

class KotlinLibraryPlugin: TravelsMarkerPlugin() {
    override fun apply(project: Project) {
    }
}