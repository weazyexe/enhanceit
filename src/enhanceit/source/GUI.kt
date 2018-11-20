package enhanceit.source

import javafx.embed.swing.SwingFXUtils
import javafx.scene.control.Button
import javafx.scene.control.Slider
import javafx.scene.image.ImageView
import marvin.image.MarvinImage

class GUI {
    companion object {
        lateinit var sliderR : Slider
        lateinit var sliderG : Slider
        lateinit var sliderB : Slider
        lateinit var applyButton : Button
        lateinit var imageView : ImageView

        @JvmStatic
        fun disableSliders() {
            sliderR.isVisible = false
            sliderG.isVisible = false
            sliderB.isVisible = false
            applyButton.isVisible = false
        }

        @JvmStatic
        fun enableSliders() {
            sliderR.isVisible = true
            sliderG.isVisible = true
            sliderB.isVisible = true
            applyButton.isVisible = true
        }

        @JvmStatic
        fun setImage() {
            imageView.image = SwingFXUtils.toFXImage(EditedImage.getBufferedImage(), null)
        }

        @JvmStatic
        fun setImage(img : MarvinImage) {
            imageView.image = SwingFXUtils.toFXImage(img.bufferedImageNoAlpha, null)
        }

    }
}