plugins {
    alias(libs.plugins.forgegradle)
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
                create("temp_mod") {
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
                create("temp_mod") {
                    source(sourceSets.main.get())
                    source(project(":common").sourceSets.main.get())
                }
            }
        }
    }
}



dependencies {
    minecraft("net.minecraftforge:forge:${libs.versions.minecraft.get()}-${libs.versions.forge.get()}")
    implementation(libs.kotlin.stdlib)
    implementation(project(":common"))
}


tasks.processResources {
    filesMatching("META-INF/mods.toml") {
        expand("version" to project.version.toString(), "mod_id" to "temp_mod")
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
