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

// Generate constants file with mod ID
tasks.register("generateConstants") {
    val outputDir = layout.buildDirectory.dir("generated/sources/constants/kotlin")
    outputs.dir(outputDir)

    doLast {
        val pkg = "${project.property("modGroup")}.${project.property("modId")}"
        val file = outputDir.get().file("$pkg/ModConstants.kt").asFile
        file.parentFile.mkdirs()
        file.writeText(
            """
            package $pkg

            object ModConstants {
                const val MOD_ID = "${project.property("modId")}"
            }
            """.trimIndent()
        )
    }
}

// Add generated sources to the main source set
sourceSets.main {
    java.srcDir(tasks.named("generateConstants"))
}