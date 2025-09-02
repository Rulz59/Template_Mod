plugins {
    kotlin("jvm")
    id("fabric-loom") version "1.6-SNAPSHOT"
}

dependencies {
    minecraft("com.mojang:minecraft:1.20.1")
    mappings("net.fabricmc:yarn:1.20.1+build.10:v2")
    modImplementation("net.fabricmc:fabric-loader:0.15.6")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.92.0+1.20.1")

    implementation(project(":common"))
}
