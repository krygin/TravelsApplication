import org.gradle.kotlin.dsl.`kotlin-dsl`
import org.gradle.kotlin.dsl.plugins.dsl.KotlinDslCompilerPlugins



buildscript {
    val kotlinVersion: String by System.getProperties()
    val gradlePluginVersion: String by System.getProperties()
    repositories {
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        jcenter()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
    configurations.all {
        resolutionStrategy {
            failOnVersionConflict()
            force("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
            force("org.jetbrains.kotlin:kotlin-gradle-plugin-api:$kotlinVersion")
        }
    }
}


plugins {
    `java-gradle-plugin`
    `kotlin-dsl` apply false
}

apply(plugin="kotlin")
apply<KotlinDslCompilerPlugins>()
apply(from="dependencies.gradle.kts")