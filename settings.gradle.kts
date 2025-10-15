pluginManagement {
    repositories {
        maven {
            url = uri("https://maven.minecraftforge.net/")
            content { includeGroup("net.minecraftforge.gradle") }
        }
        maven { url = uri("https://maven.minecraftforge.net/") }
        maven { url = uri("https://maven.fabricmc.net/") }
        gradlePluginPortal()
        mavenCentral()
    }
}
rootProject.name = "temp_mod"
include("common","fabric","forge")
