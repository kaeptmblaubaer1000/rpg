import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    dependencies {
    }

    repositories {
        jcenter()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    application
    java
    maven
    id("org.jetbrains.kotlin.jvm") version "1.3.20"
    id("org.jetbrains.dokka") version "0.9.17"
    id("org.openjfx.javafxplugin") version "0.0.7" apply false
}


application {
    mainClassName = "de.computercamp.rpg.Main"
}

val rpgVersion = File(projectDir, "version").readText(Charsets.UTF_8)
version = rpgVersion

val jar: Jar by tasks
jar.run {
    version = rpgVersion
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.run {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

if (JavaVersion.current().isJava11Compatible()) {
    apply(from = "openjfx.gradle.kts")
} else {
    dependencies {
        implementation("org.openjfx:javafx-controls:11.0.2:linux")
        implementation("org.openjfx:javafx-graphics:11.0.2:linux")
        implementation("org.openjfx:javafx-graphics:11.0.2:mac")
        implementation("org.openjfx:javafx-graphics:11.0.2:win")
        implementation("org.openjfx:javafx-base:11.0.2:linux")
        implementation("org.openjfx:javafx-fxml:11.0.2:linux")
    }
}
