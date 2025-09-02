//import org.jetbrains.kotlin.js.backend.ast.JsEmpty.setSource
//import sun.jvmstat.monitor.MonitoredVmUtil.jvmArgs
val neoForgeVersion: String by project



plugins {
    kotlin("jvm")
    id("net.neoforged.gradle.userdev") version "7.0.92"
}

repositories {
    mavenCentral()
    maven("https://maven.neoforged.net/releases")
}

dependencies {
    implementation(project(":common"))
    implementation("net.neoforged:neoforge:${neoForgeVersion}")
}

val runJvmArgs: String by project

runs {
    configureEach {
        workingDirectory = file("../run")
        modSource(project.sourceSets.main.get())
        jvmArguments(runJvmArgs.split(" "))
    }
    create("client")
}


tasks.processResources {
    filesMatching("META-INF/neoforge.mods.toml") {
        expand(inputs.properties)
    }
}
