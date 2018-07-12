val kotlinVersion: String by System.getProperties()
val gradlePluginVersion: String by System.getProperties()


dependencies {
    "compileOnly"(gradleKotlinDsl())
    "compile"(kotlin("gradle-plugin", kotlinVersion))
    "compile"("com.android.tools.build:gradle:$gradlePluginVersion")
}

repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    jcenter()
    google()
}

configurations.all {
    resolutionStrategy {
        failOnVersionConflict()
        force("com.google.guava:guava:23.0")
        force("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
        force("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
        force("com.google.errorprone:error_prone_annotations:2.2.0")
        force("org.apache.httpcomponents:httpcore:4.4.5")
        force("com.google.code.gson:gson:2.8.0")
    }
}