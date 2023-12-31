package com.example.ragagui

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class HelloApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("hello-view.fxml"))
        val scene = Scene(fxmlLoader.load(), 420.0, 260.0)
        scene.stylesheets.add(
            HelloApplication::class.java.getResource("dark-theme.css")?.toExternalForm() ?: throw IllegalStateException(
                "Stylesheet not found"
            )
        )
        stage.title = "Ragalahari Downloader!"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}