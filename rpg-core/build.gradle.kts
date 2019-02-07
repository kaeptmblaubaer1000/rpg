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
apply<CommonPlugin>()


application {
    mainClassName = "de.computercamp.rpg.Main"
}


val jar: Jar by tasks
jar.run {
    archiveVersion.set(project.version.toString())
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains:annotations:17.0.0")
    implementation("org.openjfx:javafx-graphics:11.0.2:linux")
    implementation("org.openjfx:javafx-graphics:11.0.2:mac")
    implementation("org.openjfx:javafx-graphics:11.0.2:win")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.1.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.1.0")
    testCompileOnly("junit:junit:4.12")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.1.0")
    testImplementation(kotlin("test-junit5"))
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.run {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs.plusElement("-Xuse-experimental=kotlin.contracts.ExperimentalContracts")
    }
}

if (JavaVersion.current().isJava11Compatible) {
    apply(from = "../openjfx.gradle")
} else {
    dependencies {
        implementation("org.openjfx:javafx-controls:11.0.2:linux")
        implementation("org.openjfx:javafx-base:11.0.2:linux")
        implementation("org.openjfx:javafx-fxml:11.0.2:linux")
    }
}

tasks.test {
    useJUnitPlatform()
}
