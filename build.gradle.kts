import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    java
    maven
    id("org.jetbrains.kotlin.jvm") version "1.2.60"
    id("org.jetbrains.dokka") version "0.9.17"
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
    mavenCentral()
    jcenter()
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.run {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
