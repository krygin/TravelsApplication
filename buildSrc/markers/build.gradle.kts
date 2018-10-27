import org.gradle.api.internal.artifacts.dependencies.DefaultSelfResolvingDependency
import org.gradle.api.internal.file.DefaultFileCollectionFactory
import org.gradle.api.internal.file.FileCollectionInternal
import org.gradle.kotlin.dsl.provider.gradleKotlinDslOf

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

fun String.toProperty() = (gradle as ExtensionAware).extensions.extraProperties.get(this) as String

val kotlinVersion: String = "kotlinVersion".toProperty()
val androidGradlePluginVersion: String = "androidGradlePluginVersion".toProperty()

dependencies {
    implementation(kotlin("gradle-plugin", kotlinVersion))
    implementation("com.android.tools.build:gradle:$androidGradlePluginVersion")
}

configure<GradlePluginDevelopmentExtension> {
    plugins {
        create("travelsApplication") {
            id = "com.travels.application"
            implementationClass = "com.travels.android.build_src.plugins.ApplicationPlugin"
        }
        create("travelsAndroidLibrary") {
            id = "com.travels.library"
            implementationClass = "com.travels.android.build_src.plugins.AndroidLibraryPlugin"
        }
        create("travelsKotlinLibrary") {
            id = "com.travels.kotlin"
            implementationClass = "com.travels.android.build_src.plugins.KotlinLibraryPlugin"
        }
        create("travelsFeatureLibrary") {
            id = "com.travels.feature"
            implementationClass = "com.travels.android.build_src.plugins.AndroidFeaturePlugin"
        }
    }
}
