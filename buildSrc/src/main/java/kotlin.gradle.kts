import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.dokka")
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    "implementation"(kotlin("stdlib-jdk8"))
    "implementation"("org.jetbrains:annotations:17.0.0")
    "testImplementation"("org.junit.jupiter:junit-jupiter-api:5.1.0")
    "testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:5.1.0")
    "testCompileOnly"("junit:junit:4.12")
    "testRuntimeOnly"("org.junit.vintage:junit-vintage-engine:5.1.0")
    "testImplementation"(kotlin("test-junit5"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs.plusElement("-Xuse-experimental=kotlin.contracts.ExperimentalContracts")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
