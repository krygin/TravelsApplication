import org.gradle.api.internal.artifacts.dependencies.DefaultSelfResolvingDependency
import org.gradle.api.internal.file.DefaultFileCollectionFactory
import org.gradle.api.internal.file.FileCollectionInternal
import org.gradle.kotlin.dsl.`kotlin-dsl`
import org.gradle.kotlin.dsl.plugins.dsl.KotlinDslCompilerPlugins
import org.gradle.kotlin.dsl.provider.gradleKotlinDslOf

buildscript {
    fun String.toProperty() = (gradle as ExtensionAware).extensions.extraProperties.get(this) as String
    val kotlinVersion = "kotlinVersion".toProperty()
    println("kotlinVersion: $kotlinVersion")
    repositories {
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        jcenter()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
    configurations.all {
        resolutionStrategy {
            eachDependency {
                if (requested.group == "org.jetbrains.kotlin") {
                    useVersion(kotlinVersion)
                    because("kotlin dsl packs embedded kotlin dependencies (often with older version)")
                }
            }
            failOnVersionConflict()
        }
    }
}


plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

apply(plugin = "kotlin")
apply<KotlinDslCompilerPlugins>()

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

inline fun <reified T> String.toProperty() = (gradle as ExtensionAware).extensions.extraProperties.get(this) as T

val kotlinVersion: String = "kotlinVersion".toProperty()
val gradlePluginVersion: String = "gradlePluginVersion".toProperty()
val guavaJavaVersion: String = "guavaJavaVersion".toProperty()
val gsonVersion: String = "gsonVersion".toProperty()
val httpCoreVersion: String = "httpCoreVersion".toProperty()
val errorProneAnnotationsVersion: String = "errorProneAnnotationsVersion".toProperty()

System.setProperty("kotlinVersion", kotlinVersion)
System.setProperty("gradlePluginVersion", gradlePluginVersion)
System.setProperty("guavaJavaVersion", guavaJavaVersion)
System.setProperty("gsonVersion", gsonVersion)
System.setProperty("httpCoreVersion", httpCoreVersion)
System.setProperty("errorProneAnnotationsVersion", errorProneAnnotationsVersion)



repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    jcenter()
    google()
}

dependencies {
    "compileOnly"(gradleKotlinDsl())
    "compile"(kotlin("gradle-plugin", kotlinVersion))
    "compile"("com.android.tools.build:gradle:$gradlePluginVersion")
}

configurations {
    all {
        resolutionStrategy {
            eachDependency {
                if (requested.group == "org.jetbrains.kotlin") {
                    useVersion(kotlinVersion)
                    because("kotlin dsl packs embedded kotlin dependencies (often with older version)")
                }
            }
            force("com.google.guava:guava:$guavaJavaVersion")
            force("com.google.code.gson:gson:$gsonVersion")
            force("org.apache.httpcomponents:httpcore:$httpCoreVersion")
            force("com.google.errorprone:error_prone_annotations:$errorProneAnnotationsVersion")
            failOnVersionConflict()
        }

    }
}

/**
 * Creates a dependency on the API of the current version of the Gradle Kotlin DSL.
 * Filters out kotlin jars to avoid duplicates in buildSrc classpath
 */
fun Project.gradleKotlinDsl(): Dependency =
        DefaultSelfResolvingDependency(
                fileCollectionOf(
                        gradleKotlinDslOf(project).filter { !isKotlinJar(it.name) }, "gradleKotlinDsl"
                ) as FileCollectionInternal
        )

fun fileCollectionOf(files: Collection<File>, name: String): FileCollection =
        DefaultFileCollectionFactory().fixed(name, files)

fun isKotlinJar(name: String): Boolean = name.startsWith("kotlin-stdlib-") || name.startsWith("kotlin-reflect-")