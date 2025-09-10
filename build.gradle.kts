import org.gradle.api.plugins.JavaPluginExtension

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

    // A flag to check if the project is part of the NeoForge subproject.
    val isNeoForge = project.name == "neoforge"

    // Only apply fabric-loom's IDE tasks if the project is not the neoforge subproject.
    if (project.name == "fabric") {
        tasks.withType<Task>().configureEach {
            // Check if the task is an IDE sync task provided by fabric-loom.
            // This is a simple heuristic, as the task name often includes "idea" or "eclipse".
            // You can be more specific if needed.
            if (this.name == "genIdeaRuns" || this.name == "genEclipseRuns") {
                this.enabled = false
            }
        }
    }
}
