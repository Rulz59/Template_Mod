plugins {
    id("net.minecraftforge.gradle") version "6.0.24" // or 6.0.51 if available
    kotlin("jvm") version "1.9.23"
}

group = "com.yourorg"
version = "1.0.0"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
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
    mappings("official", "1.20.1")

    runs {
        create("client") {
            workingDirectory(project.file("run"))
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")

            // 👇 This ensures CommonMod is on the runtime classpath
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
    minecraft("net.minecraftforge:forge:$minecraftVersion-$forgeVersion")
    implementation(kotlin("stdlib"))
    implementation(project(":common"))
    // implementation("thedarkcolour:kotlinforforge:4.3.0")
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
