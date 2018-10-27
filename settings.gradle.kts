import org.gradle.kotlin.dsl.support.kotlinEap

val kotlinVersion: String by settings



pluginManagement {
    repositories {
        kotlinEap()
        jcenter()
        google()
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


rootProject.buildFileName = "build.gradle.kts"


include(
        ":main",
        ":main-search",
        ":journeys-create",
        ":journeys-filter",
        ":journeys:domain",
        ":app",
        ":base",
        ":instantapp",
        ":auth",
        ":design:date-range-picker",
        ":design:route-widget",
        ":data"
)