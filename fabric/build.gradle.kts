plugins {
    id("fabric-loom") version "1.5-SNAPSHOT"
    kotlin("jvm")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
    withSourcesJar()
}

dependencies {
    // Minecraft + mappings
    minecraft("com.mojang:minecraft:1.20.1")
    mappings("net.fabricmc:yarn:1.20.1+build.10:v2")

    // Fabric loader + API
    modImplementation("net.fabricmc:fabric-loader:0.15.11")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.92.0+1.20.1")

    // Kotlin stdlib
    implementation(kotlin("stdlib"))

    // Depend on common module
    implementation(project(":common"))
}



tasks.processResources {
    filesMatching("fabric.mod.json") {
        expand(
            mapOf(
                "version" to "0.1.0",
                "minecraft_version" to "1.20.1"
            )
        )
    }
}

