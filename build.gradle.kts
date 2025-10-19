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

// Refresh Mod Directories
tasks.register("refresh-assets") {
    val modId = project.property("modId") as String

    // Directories where a modId subfolder exists
    val targets = listOf(
        "common/src/main/resources/assets",
        "forge/src/main/kotlin/net/rulz59",
        "fabric/src/main/kotlin/net/rulz59"
    )

    doLast {
        targets.forEach { base ->
            val baseDir = file(base)
            if (!baseDir.exists()) return@forEach

            // Find the first subdirectory
            val existing = baseDir.listFiles()?.firstOrNull { it.isDirectory }
            if (existing != null && existing.name != modId) {
                val newDir = baseDir.resolve(modId)
                println("Renaming ${existing.path} -> ${newDir.path}")
                existing.copyRecursively(newDir, overwrite = true)
                existing.deleteRecursively()
            }
        }
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