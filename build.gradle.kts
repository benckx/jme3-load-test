plugins {
    alias(libs.plugins.versions)
    alias(libs.plugins.kotlin.jvm)
    application
    idea
}

repositories {
    mavenCentral()
    google()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation(libs.kotlin.stdlib)

    // jme3
    implementation(libs.jme.core)
    implementation(libs.jme.desktop)
    implementation(libs.jme.lwjgl3)

    // jme3 helper libs
    implementation(libs.chimp.utils.basics)
    implementation(libs.chimp.utils.jme3)
    implementation(libs.ouistiti)

    // other libs
    implementation(libs.commons.lang3)
}

application {
    mainClass = "MainKt"
}
