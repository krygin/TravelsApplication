package com.travels.android.build_src.util

import org.gradle.api.Plugin
import org.gradle.api.Project

inline fun <reified T: Plugin<Project>> Set<Project>.withType(action: (project: Project, plugin: Plugin<Project>) -> Unit) {
    forEach { project ->
        project.plugins.withType(T::class.java).forEach { plugin ->
            action(project, plugin)
        }
    }
}