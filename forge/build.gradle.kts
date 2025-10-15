plugins {
    alias(libs.plugins.forgeGradle)
    alias(libs.plugins.kotlin)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(libs.versions.java.get().toInt()))
    withSourcesJar()
}


repositories {
    mavenCentral()
    maven { url = uri("https://maven.minecraftforge.net/") }
    maven { url = uri("https://thedarkcolour.github.io/KotlinForForge/") }
}

val minecraftVersion = "1.20.1"
val forgeVersion = "47.1.3"

minecraft {
    mappings("official", libs.versions.minecraft.get())
    runs {
        create("client") {
            workingDirectory(project.file("run"))
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            mods {
                create("tempmod") {
                    source(sourceSets.main.get())
                    source(project(":common").sourceSets.main.get())
                }
            }
        }
        create("server") {
            workingDirectory(project.file("run"))
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            mods {
                create("tempmod") {
                    source(sourceSets.main.get())
                    source(project(":common").sourceSets.main.get())
                }
            }
        }
    }
}



dependencies {
    minecraft("net.minecraftforge:forge:${libs.versions.minecraft.get()}-${libs.versions.forge.get()}")
    implementation(libs.kotlinStdlib)
    implementation(project(":common"))
}


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
            "modIcon" to project.property("modIcon")
        )
    }
}


tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "17"
}

sourceSets {
    main {
        java.setSrcDirs(listOf("src/main/kotlin"))
    }
}
