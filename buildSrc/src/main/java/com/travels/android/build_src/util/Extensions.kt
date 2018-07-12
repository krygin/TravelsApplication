package com.travels.android.build_src.util

import org.gradle.api.plugins.ExtensionContainer
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.AppExtension

fun ExtensionContainer.android(configure: BaseExtension.() -> Unit) {
    findByType(AppExtension::class.java)?.let(configure) ?:
            findByType(LibraryExtension::class.java)?.let(configure)
}