// This script configures the Gradle build for a multi-project setup.

// Set the root project's name.
rootProject.name = "MyMod"

// Include all subprojects in the build.
include("common", "fabric", "neoforge")

// Configure how dependencies are resolved.
dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

// Manage plugins for all projects.
pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
        maven("https://maven.neoforged.net/releases")
        mavenCentral()
    }
}
