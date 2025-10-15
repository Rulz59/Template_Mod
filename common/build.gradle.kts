plugins {
    kotlin("jvm")
    `java-library`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
    withSourcesJar()
}

dependencies {
    // Only standard Kotlin here, keep it loader-agnostic
    //api(kotlin("stdlib"))
}
