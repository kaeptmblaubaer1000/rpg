buildscript {
    repositories {
        jcenter()
        gradlePluginPortal()
    }
}

plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    gradlePluginPortal()
}

dependencies {
    api("org.jetbrains.gradle.plugin.idea-ext:org.jetbrains.gradle.plugin.idea-ext.gradle.plugin:0.5")
    api("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:1.3.20")
    api("org.jetbrains.dokka:org.jetbrains.dokka.gradle.plugin:0.9.17")
}
