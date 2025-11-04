val modId: String by settings
rootProject.name = modId

pluginManagement {
    repositories {
        maven { url = uri("https://maven.minecraftforge.net/") }
        maven { url = uri("https://maven.fabricmc.net/") }
        gradlePluginPortal()
        mavenCentral()
    }
}

include("common","fabric","forge")
