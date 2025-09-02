rootProject.name = "MyMod"

include("common", "fabric", "neoforge")

pluginManagement {
    plugins {
        id("net.neoforged.gradle.userdev") version "7.0.92"
    }
    repositories {
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
        maven("https://maven.neoforged.net/releases")
        mavenCentral()
    }
}



