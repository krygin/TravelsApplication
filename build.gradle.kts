import com.android.build.gradle.FeaturePlugin
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.BaseExtension
import com.travels.android.build_src.Dependencies
import com.travels.android.build_src.Versions
import com.travels.android.build_src.plugins.AnyAndroidPlugin
import com.travels.android.build_src.plugins.ApplicationPlugin
import com.travels.android.build_src.util.withType
import com.travels.android.build_src.Dependencies.allProjectDependencies
import com.travels.android.build_src.plugins.KotlinLibraryPlugin
import com.vanniktech.dependency.graph.generator.DependencyGraphGeneratorExtension
import com.vanniktech.dependency.graph.generator.DependencyGraphGeneratorExtension.Generator
import org.jetbrains.kotlin.gradle.plugin.KotlinAndroidPluginWrapper


// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlinVersion: String by project
    val androidGradlePluginVersion: String by project
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:$androidGradlePluginVersion")
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
    version = "4.10.2"
    distributionType = Wrapper.DistributionType.BIN
}

subprojects {
    plugins.withType<KotlinAndroidPluginWrapper> {
        configure<BaseExtension> {
            sourceSets {
                getByName("main").java.srcDir("src/main/kotlin")
                getByName("androidTest").java.srcDir("src/androidTest/kotlin")
                getByName("test").java.srcDir("src/test/kotlin")
            }
        }
    }

    plugins.withType<AnyAndroidPlugin> {
        configure<BaseExtension> {
            compileSdkVersion(Versions.compileSdkVersion)
            defaultConfig {
                minSdkVersion(21)
                targetSdkVersion(Versions.targetSdkVersion)
                versionCode = 1
                versionName = "1.0"
            }

            val javaVersion = JavaVersion.VERSION_1_8

            compileOptions {
                setSourceCompatibility(javaVersion)
                setTargetCompatibility(javaVersion)
            }

            packagingOptions {
                exclude("META-INF/rxjava.properties")
                exclude("META-INF/rxkotlin.properties")
            }
        }
    }

    plugins.withType<KotlinLibraryPlugin> {
        configurations.all {
            applyDefaultResolutionStrategy()
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


fun Configuration.applyDefaultResolutionStrategy() {
    exclude(module = "commons-logging")
    exclude(group = "org.codehaus.plexus")

    if (!project.hasProperty("disableForceDependencies")) {
        resolutionStrategy {
            failOnVersionConflict()
            allProjectDependencies.forEach { dependency: String ->
                force(dependency)
            }
        }
    }
}