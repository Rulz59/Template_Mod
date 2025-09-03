rootProject.name = "MyMod"

include("common", "fabric", "neoforge")

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
        maven("https://maven.neoforged.net/releases")
        mavenCentral()
    }
}



