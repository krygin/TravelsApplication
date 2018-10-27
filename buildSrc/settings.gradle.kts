import org.gradle.kotlin.dsl.support.kotlinEap
import java.io.FileInputStream
import java.util.Properties


include(":markers")
include(":script")


/**
 * WARNING! CLI P-overrides will be ignored
 * https://docs.gradle.org/current/userguide/build_environment.html#sec:gradle_configuration_properties
 */
file("../gradle.properties").transferProperties()
file("${System.getProperty("user.home")}/.gradle/gradle.properties").transferProperties()

val kotlinVersion: String = "kotlinVersion".toProperty()

pluginManagement {
    repositories {
        jcenter()
        kotlinEap()
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("org.jetbrains.kotlin")) {
                useVersion(kotlinVersion)
            }
        }
    }
}

fun File.transferProperties() {
    if (exists()) {
        FileInputStream(this@transferProperties).use { inputStream ->
            with(Properties()) {
                load(inputStream)
                forEach { property: Map.Entry<Any, Any> ->
                    property.transferProperty()
                }
            }
        }
    }
}

fun Map.Entry<Any, Any>.transferProperty() {
    val key = this.key as String
    //https://docs.gradle.org/current/userguide/build_environment.html#sec:gradle_system_properties
    val isNotSystemProperty = !key.startsWith("systemProp.")
    if (isNotSystemProperty) {
        //System property is set via -D value
        val value = System.getProperty(key) ?: this.value as String
        (gradle as ExtensionAware).extensions.extraProperties.set(key, value)
    }
}


fun File.readProperties(getFunction: Properties.() -> Unit) {
    FileInputStream(this@readProperties).use { inputStream ->
        with(Properties()) {
            load(inputStream)
            getFunction(this@with)
        }
    }
}

fun String.toProperty() = (gradle as ExtensionAware).extensions.extraProperties.get(this) as String