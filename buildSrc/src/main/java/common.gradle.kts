plugins {
    maven
    idea
    id("org.jetbrains.gradle.plugin.idea-ext")
    eclipse
}


tasks.withType<Jar> {
    archiveVersion.set(project.version.toString())
}
