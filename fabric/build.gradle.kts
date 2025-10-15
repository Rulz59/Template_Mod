plugins {
    alias(libs.plugins.loom)
    alias(libs.plugins.kotlin)
}


java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(libs.versions.java.get().toInt()))
    withSourcesJar()
}


dependencies {
    minecraft("com.mojang:minecraft:${libs.versions.minecraft.get()}")
    mappings("net.fabricmc:yarn:${libs.versions.minecraft.get()}+build.10:v2")

    modImplementation("net.fabricmc:fabric-loader:${libs.versions.fabric.loader.get()}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.92.0+${libs.versions.minecraft.get()}")

    implementation(libs.kotlin.stdlib)
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

