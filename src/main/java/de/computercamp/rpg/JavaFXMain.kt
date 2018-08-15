package de.computercamp.rpg

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.input.KeyCombination
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.stage.Stage

class JavaFXMain : Application() {
    private lateinit var standardScene: Scene

    private lateinit var stage: Stage

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     *
     *
     * NOTE: This method is called on the JavaFX Application Thread.
     *
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched as an applet.
     * Applications may create other stages, if needed, but they will not be
     * primary stages and will not be embedded in the browser.
     */
    override fun start(primaryStage: Stage) {
        val pane = GridPane()
        this.standardScene = Scene(pane)
        this.stage = primaryStage
        primaryStage.scene = standardScene
        primaryStage.isFullScreen = true
        primaryStage.fullScreenExitKeyCombination = KeyCombination.NO_MATCH
        standardScene.fill = Color.BLACK
        primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Application.launch(JavaFXMain::class.java, *args)
        }
    }
}
