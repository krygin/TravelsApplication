import com.android.build.gradle.FeaturePlugin
import com.android.build.gradle.LibraryPlugin
import com.travels.android.build_src.Versions
import com.travels.android.build_src.plugins.AnyAndroidPlugin
import com.travels.android.build_src.plugins.ApplicationPlugin
import com.travels.android.build_src.util.withType
import com.travels.android.build_src.util.android
import com.vanniktech.dependency.graph.generator.DependencyGraphGeneratorExtension
import com.vanniktech.dependency.graph.generator.DependencyGraphGeneratorExtension.Generator


// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlinVersion: String by System.getProperties()
    val gradlePluginVersion: String by System.getProperties()
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:$gradlePluginVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.vanniktech:gradle-dependency-graph-generator-plugin:0.5.0")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://jitpack.io")
        maven("https://maven.google.com")
    }
}

val clean by tasks.creating(Delete::class) {
    delete(rootProject.buildDir)
}

val wrapper by tasks.creating(Wrapper::class) {
    version = "4.9.1-rc-1"
    distributionType = Wrapper.DistributionType.BIN
}

subprojects.withType<AnyAndroidPlugin> { project, plugin ->
    with(project) {
        apply {
            plugin("kotlin-android")
            plugin("kotlin-android-extensions")
        }
        extensions.android {
            compileSdkVersion(Versions.compileSdkVersion)
            defaultConfig {
                minSdkVersion(21)
                targetSdkVersion(Versions.targetSdkVersion)
                versionCode = 1
                versionName = "1.0"
            }

            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                }
            }
        }
    }
}

apply(plugin = "com.vanniktech.dependency.graph.generator")

val projectLibraries = Generator(
        name = "projectLibraries",
        include = { dependency -> dependency.moduleGroup.startsWith(rootProject.name) }
)

configure<DependencyGraphGeneratorExtension> {
    generators = arrayListOf(projectLibraries)
}