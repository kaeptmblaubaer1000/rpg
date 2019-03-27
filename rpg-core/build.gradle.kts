buildscript {
    dependencies {
    }

    repositories {
        jcenter()
        gradlePluginPortal()
    }
}

plugins {
    application
    id("org.openjfx.javafxplugin") version "0.0.7" apply false
}
apply<KotlinPlugin>()
apply<CommonPlugin>()


application {
    mainClassName = "de.computercamp.rpg.Main"
}


dependencies {
    implementation("org.openjfx:javafx-graphics:11.0.2:linux")
    implementation("org.openjfx:javafx-graphics:11.0.2:mac")
    implementation("org.openjfx:javafx-graphics:11.0.2:win")
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
