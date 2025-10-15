import net.minecraftforge.gradle.common.util.RunConfig
val modId: String by project

// Plugins for Forge subproject
plugins {
    alias(libs.plugins.forgeGradle)
    alias(libs.plugins.kotlin)
}

// Java toolchain and source jar
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(libs.versions.java.get().toInt()))
    withSourcesJar()
}

// Repositories for dependencies
repositories {
    maven { url = uri("https://maven.minecraftforge.net/") }
    maven { url = uri("https://thedarkcolour.github.io/KotlinForForge/") }
}

// Helper function to configure run configurations
fun NamedDomainObjectContainer<RunConfig>.configureRun(name: String, modId: String) {
    create(name) {
        workingDirectory(project.file("run"))
        property("forge.logging.markers", "REGISTRIES")
        property("forge.logging.console.level", "debug")
        mods {
            create(modId) {
                source(sourceSets.main.get())
                source(project(":common").sourceSets.main.get())
            }
        }
    }
}

// Minecraft configuration with mappings and run configurations
minecraft {
    mappings("official", libs.versions.minecraft.get())
    runs {
        configureRun("client", modId)
        configureRun("server", modId)
    }
}

// Dependencies for Minecraft mod
dependencies {
    minecraft("net.minecraftforge:forge:${libs.versions.minecraft.get()}-${libs.versions.forge.get()}")
    implementation(libs.kotlinStdlib)
    implementation(project(":common"))
}

// Process resources task to include common resources and expand mod metadata
tasks.processResources {
    from(project(":common").sourceSets.main.get().resources)
    filesMatching("META-INF/mods.toml") {
        expand(
            "modId" to project.property("modId"),
            "modName" to project.property("modName"),
            "modVersion" to project.property("modVersion"),
            "modLicense" to project.property("modLicense"),
            "modDescription" to project.property("modDescription"),
            "modIssues" to project.property("modIssues"),
            "modAuthors" to project.property("modAuthors"),
            "modIcon" to project.property("modIcon"),
            "forgeLoaderVersion" to project.property("forgeLoaderVersion")
        )
    }
}

// Set JVM target for Kotlin compilation
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = libs.versions.java.get()
}

// Configure source sets to use Kotlin source directory
sourceSets {
    main {
        java.setSrcDirs(listOf("src/main/kotlin"))
    }
}
