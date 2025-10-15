// Apply plugins common to all subprojects
plugins {
    alias(libs.plugins.kotlin)
    `java-library`
}

// Configure Java toolchain and source jar generation
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(libs.versions.java.get().toInt()))
    withSourcesJar()
}