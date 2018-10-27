inline fun <reified T> String.toProperty() =
        (gradle as ExtensionAware).extensions.extraProperties.get(this) as T

val targetSdk: String = "targetSdk".toProperty()
val kotlinVersion: String = "kotlinVersion".toProperty()
val androidGradlePluginVersion: String = "androidGradlePluginVersion".toProperty()

allprojects {
    /**
     * Preserve order of repositories due to accidental wrong google-android libraries resolving in jcenter
     */
    repositories {
        google()
        jcenter()
    }
}

/**
 * Technically we don't need it here, but IDE goes crazy and does not resolve script dependencies
 */
plugins {
    kotlin("jvm")
}

subprojects {

    /**
     * add all plugins from subprojects to buildSrc output
     * https://github.com/gradle/gradle/blob/ecda3c82dd42794ef6da6bb7338c9fcb3e5e575a/subprojects/docs/src/samples/multiProjectBuildSrc/buildSrc/build.gradle#L10
     */
    rootProject.dependencies {
        runtime(project(this@subprojects.path))
    }
}