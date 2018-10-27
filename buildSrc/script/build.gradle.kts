import org.gradle.api.internal.artifacts.dependencies.DefaultSelfResolvingDependency
import org.gradle.api.internal.artifacts.ivyservice.dependencysubstitution.DefaultDependencySubstitutions
import org.gradle.api.internal.file.DefaultFileCollectionFactory
import org.gradle.api.internal.file.FileCollectionInternal
import org.gradle.kotlin.dsl.provider.gradleKotlinDslOf


plugins {
    `kotlin-dsl`
}

fun String.toProperty() = (gradle as ExtensionAware).extensions.extraProperties.get(this) as String

val kotlinVersion: String = "kotlinVersion".toProperty()
val androidGradlePluginVersion: String = "androidGradlePluginVersion".toProperty()

dependencies {
    implementation(kotlin("stdlib", kotlinVersion))
    implementation("com.android.tools.build:gradle:$androidGradlePluginVersion")

    testImplementation("junit:junit:4.12")
    testImplementation("ch.tutteli.atrium:atrium-cc-en_GB-robstoll:0.7.0")
}

//Must always share the common libs versions
val targetSdk: String = "targetSdk".toProperty()
val errorProneAnnotationsVersion: String = "errorProneAnnotationsVersion".toProperty()
val guavaJavaVersion: String = "guavaJavaVersion".toProperty()
val httpCoreVersion: String = "httpCoreVersion".toProperty()
val gsonVersion: String = "gsonVersion".toProperty()
val okioVersion: String = "okioVersion".toProperty()


val fasterXmlVersion = "2.9.6"

/**
 * pass versions to code
 */
System.setProperty("kotlinVersion", kotlinVersion)
System.setProperty("androidGradlePluginVersion", androidGradlePluginVersion)
System.setProperty("targetSdk", targetSdk)
System.setProperty("gsonVersion", gsonVersion)
System.setProperty("okioVersion", okioVersion)

