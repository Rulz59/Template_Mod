// Workaround: limit Kotlin's configureDefaultVersionsResolutionStrategy to compile-only configs
gradle.projectsEvaluated {
    configurations.matching { it.name != "runtimeClasspath" }.all {
        // no-op, ensures kotlin action runs on others; this is a heuristic and may be required to tweak
    }
}

plugins {
    kotlin("jvm") version "1.9.23"
}

group = "net.rulz59"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

allprojects {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/") // Fabric’s Maven
    }
}