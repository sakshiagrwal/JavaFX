package com.example.ragagui

import javafx.fxml.FXML
import java.net.URL
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import org.jsoup.Jsoup
import java.nio.file.Files

class HelloController {

    @FXML
    private lateinit var textArea: TextArea // TextArea for output

    @FXML
    private lateinit var urlField: TextField // TextField for URL input

    @FXML
    fun initialize() {
        // Initialization code if needed
    }

    @FXML
    fun onFetchButtonClicked() {
        val url = urlField.text // Get the URL from the TextField
        val doc = Jsoup.connect(url).get()
        val images = doc.select("img[src$=t.jpg]")

        val imageUrls = images.map { it.absUrl("src").replace("t.jpg", ".jpg") }
        textArea.clear() // Clear the TextArea before appending new URLs
        imageUrls.forEach { imageUrl ->
            textArea.appendText("JPG URL: $imageUrl\n")
        }
    }

    @FXML
    fun onDownloadButtonClicked() {
        val directory = Paths.get("downloaded_images") // Define the directory path for downloaded images
        if (!Files.exists(directory)) {
            Files.createDirectories(directory) // Create the directory if it doesn't exist
        }

        val imageUrls = textArea.text.lines().filter { it.startsWith("JPG URL: ") }
            .map { it.removePrefix("JPG URL: ") }

        imageUrls.forEach { imageUrl ->
            val imageFileName = imageUrl.substringAfterLast("/")
            val imagePath = directory.resolve(imageFileName)

            try {
                val inputStream = URL(imageUrl).openStream()
                Files.copy(inputStream, imagePath, StandardCopyOption.REPLACE_EXISTING)
                textArea.appendText("Downloaded: $imageFileName\n")
            } catch (e: Exception) {
                textArea.appendText("Failed to download: $imageFileName\n")
            }
        }
    }
}