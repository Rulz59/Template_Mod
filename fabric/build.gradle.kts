plugins {
    alias(libs.plugins.loom)
    alias(libs.plugins.kotlin)
}


java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(libs.versions.java.get().toInt()))
    withSourcesJar()
}


dependencies {
    // Minecraft + mappings
    minecraft("com.mojang:minecraft:${libs.versions.minecraft.get()}")
    mappings("net.fabricmc:yarn:${libs.versions.minecraft.get()}+build.10:v2")

    // Fabric loader + API
    modImplementation("net.fabricmc:fabric-loader:${libs.versions.fabricLoader.get()}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${libs.versions.fabricApi.get()}")

    // Kotlin stdlib
    implementation(libs.kotlinStdlib)

    // Depend on common module
    implementation(project(":common"))
}





tasks.processResources {
    from(project(":common").sourceSets.main.get().resources)
    filesMatching("fabric.mod.json") {
        expand(
            "modId" to project.property("modId"),
            "modName" to project.property("modName"),
            "modVersion" to project.property("modVersion"),
            "modDescription" to project.property("modDescription"),
            "modAuthors" to project.property("modAuthors"),
            "modLicense" to project.property("modLicense"),
            "modIcon" to project.property("modIcon"),
            "modIssues" to project.property("modIssues"),
            "modGroup" to project.property("modGroup"),
            "fabricLoaderVersion" to libs.versions.fabricLoader.get(),
            "minecraftVersion" to libs.versions.minecraft.get(),
            "fabricApiVersion" to libs.versions.fabricApi.get()
        )
    }
}



