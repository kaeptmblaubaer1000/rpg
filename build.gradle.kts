import java.io.File

plugins {
    application
    java
}


java.sourceSets.run {
    getByName("main") {
        java.srcDirs.add(File(projectDir, "src"))
    }
}

application {
    mainClassName = "de.computercamp.rpg.Main"
}
