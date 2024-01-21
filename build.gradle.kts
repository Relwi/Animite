plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.dependencyAnalysis)
    id("io.gitlab.arturbosch.detekt") version "1.23.4"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
