plugins {
    kotlin("jvm") version "2.0.186"
    id("net.neoforged.moddev.legacyforge") version "2.0.106"
}

repositories {
    mavenCentral()
    maven("https://maven.neoforged.net/releases")
    maven("https://maven.minecraftforge.net")
}

val minecraftVersion: String by project // e.g. "1.20.1"
val forgeVersion: String by project     // e.g. "47.2.0"
val runJvmArgs: String by project       // optional

dependencies {
    implementation(project(":common"))

    // Use 'add' to avoid the Kotlin accessor ambiguity:
    add("minecraft", "net.minecraftforge:forge:$minecraftVersion-$forgeVersion")

    // If you prefer a typed accessor later, once accessors are generated you can switch to:
    // minecraft("net.minecraftforge:forge:$minecraftVersion-$forgeVersion")
}

minecraft {
    runs {
        create("client") {
            workingDirectory(file("./run"))
            if (project.hasProperty("mc_args")) {
                args(project.property("mc_args").toString().split(" ").filter { it.isNotBlank() })
            }
            if (runJvmArgs.isNotBlank()) {
                jvmArguments(runJvmArgs.split(" ").filter { it.isNotBlank() })
            }
        }
    }
}

tasks.processResources {
    filesMatching("META-INF/neoforge.mods.toml") {
        expand(inputs.properties)
    }
}
