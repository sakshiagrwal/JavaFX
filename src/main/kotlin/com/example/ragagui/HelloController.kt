package com.example.ragagui

import javafx.fxml.FXML
import javafx.scene.control.TextArea
import org.jsoup.Jsoup

class HelloController {

    @FXML
    private lateinit var textArea: TextArea // Assuming there's a TextArea in your FXML to display the URLs

    @FXML
    fun initialize() {
        // Initialization code if needed
    }

    @FXML
    fun onScrapeButtonClicked() { // This method is called when the scrape button is clicked
        val url = "https://www.ragalahari.com/actress/173012/neha-shetty-latest-stills-hd-gallery.aspx"
        val doc = Jsoup.connect(url).get()
        val images = doc.select("img[src$=t.jpg]")

        val imageUrls = images.map { it.absUrl("src").replace("t.jpg", ".jpg") }
        imageUrls.forEach { imageUrl ->
            textArea.appendText("JPG URL: $imageUrl\n")
        }
    }
}