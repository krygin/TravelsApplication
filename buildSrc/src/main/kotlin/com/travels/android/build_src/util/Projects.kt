package com.travels.android.build_src.util

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.PluginContainer

inline fun <reified T : Plugin<*>> PluginContainer.withType(noinline action: (plugin: T) -> Unit) {
    withType(T::class.java, action)
}

inline fun <reified T : Plugin<*>> Set<Project>.withType(noinline action: (project: Project, plugin: T) -> Unit) {
    forEach { project ->
        project.plugins.withType(T::class.java) {
            action(project, this)
        }
    }
}