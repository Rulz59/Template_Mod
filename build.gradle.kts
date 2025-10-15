// Workaround: prevent Kotlin version resolution from breaking Forge/Fabric runs
gradle.projectsEvaluated {
    configurations.matching { it.name != "runtimeClasspath" }.all { /* no-op */ }
}

// Apply plugins to the root project
plugins {
    alias(libs.plugins.kotlin)
}

// Set Java toolchain version for Kotlin compilation
kotlin {
    jvmToolchain(libs.versions.java.get().toInt())
}

// Add common objects to all subprojects
allprojects {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

// Unit Tests
/*
dependencies {
    testImplementation(kotlin("test"))
}
tasks.test {
    useJUnitPlatform()
}
 */