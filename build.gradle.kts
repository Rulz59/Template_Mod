plugins {
    kotlin("jvm") version "1.9.23" apply false
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://maven.fabricmc.net/")
        maven("https://maven.neoforged.net/releases")
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    // Now that the plugin is applied, the 'java' extension exists
    configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }
}


