// This script uses the Kotlin DSL for Gradle.

// No need to add explicit imports for the NeoForge extensions,
// as the plugin provides them directly to the build script's classpath.

// Define a few properties for easier management.
val minecraft_version = "1.20.1"
val neoforge_version = "2.0.107-beta"
val mod_id = "mymod"
val mod_version = "1.0.0"

// Define repository sources for both plugins and dependencies.
repositories {
    // This is the default Maven repository.
    mavenCentral()
    // The NeoForge repository is crucial for finding the plugin and its dependencies.
    maven {
        // The url property expects a URI, so we use the uri() function.
        url = uri("https://maven.neoforged.net/releases")
    }
}

// Apply required plugins.
plugins {
    // The "java" plugin provides standard Java project tasks.
    java

    // The "kotlin-jvm" plugin is required for Kotlin source code.
    // Updated version to 1.9.23.
    kotlin("jvm") version "1.9.23"

    // The official NeoForge Gradle plugin.
    id("net.neoforged.moddev") version "2.0.107"
}

// Configure the NeoForge extension using its dedicated, type-safe block.
neoForge {
    // Define the ModModel for your project here, outside of the individual runs.
    mods {
        create(mod_id)
    }

    // Explicitly configure the runs extension within the NeoForge extension.
    runs {
        // Run a dedicated client for testing.
        create("client")
        // Run a dedicated server for testing.
        create("server")
    }
}

// Add the dependency on the 'common' project. This tells Gradle that
// the 'neoforge' module needs the compiled classes from the 'common' module.
dependencies {
    // The 'implementation' configuration is generally what you want for
    // project dependencies.
    implementation(project(":common"))

    // This is the crucial line that was missing. It adds the NeoForge
    // development toolkit as a dependency, which contains all the necessary
    // classes and APIs for your mod to compile against.
    implementation("net.neoforged:neoforge:$minecraft_version-$neoforge_version:dev")
}


// Configure the main jar file.
tasks.jar {
    // Make the jar file include the mod ID and version.
    archiveBaseName.set("$mod_id-$minecraft_version")
    archiveVersion.set(mod_version)

    // Add the `neoforge` dependency to the jar.
    from(sourceSets.main.get().output) {
        // Exclude properties files from the `META-INF` directory to prevent conflicts.
        exclude("META-INF/neoforge.accesswidener")
    }
}

// Set a custom target for the Java version to ensure compatibility.
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))

    // This fixes the "Not publishing sources jar" warning.
    withSourcesJar()
    withJavadocJar()
}
