plugins {
    application
    java
    maven
    id("org.jetbrains.kotlin.jvm") version "1.2.60"
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
