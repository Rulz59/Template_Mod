plugins {
    kotlin("jvm")
    id("net.neoforged.gradle") version "7.0.79"
}

dependencies {
    minecraft("net.neoforged:neoforge:1.20.1-47.1.106")

    implementation(project(":common"))
}
