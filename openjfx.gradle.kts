apply(plugin = "org.openjfx.javafxplugin")
configure<org.openjfx.gradle.JavaFXOptions> {
    modules = mutableListOf("javafx.fxml", "javafx.controls")
    version = "11.0.2"
}
