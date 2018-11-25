package enhanceit.source

import javafx.embed.swing.SwingFXUtils
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.Slider
import javafx.scene.control.Tooltip
import javafx.scene.image.ImageView
import javafx.stage.Stage
import marvin.image.MarvinImage


class GUI {
    companion object {
        lateinit var sliderR : Slider
        lateinit var sliderG : Slider
        lateinit var sliderB : Slider
        lateinit var applyRGBButton : Button
        lateinit var imageView : ImageView
        lateinit var brightnessSlider: Slider
        lateinit var contrastSlider: Slider
        lateinit var brightnessLabel: Label
        lateinit var contrastLabel: Label
        lateinit var applyBrightnessButton: Button
        lateinit var noiseButton: Button
        lateinit var colorButton: Button
        lateinit var bwButton: Button
        lateinit var brightnessButton: Button
        lateinit var autoButton: Button
        lateinit var saveButton: Button

        @JvmStatic
        fun disableSlidersRGB() {
            sliderR.isVisible = false
            sliderG.isVisible = false
            sliderB.isVisible = false
            applyRGBButton.isVisible = false
        }

        @JvmStatic
        fun enableSlidersRGB() {
            sliderR.isVisible = true
            sliderG.isVisible = true
            sliderB.isVisible = true
            applyRGBButton.isVisible = true
        }

        @JvmStatic
        fun disableSlidersBC() {
            brightnessSlider.isVisible = false
            contrastSlider.isVisible = false
            brightnessLabel.isVisible = false
            contrastLabel.isVisible = false
            applyBrightnessButton.isVisible = false
        }

        @JvmStatic
        fun enableSlidersBC() {
            brightnessSlider.isVisible = true
            contrastSlider.isVisible = true
            brightnessLabel.isVisible = true
            contrastLabel.isVisible = true
            applyBrightnessButton.isVisible = true
        }

        @JvmStatic
        fun setImage() {
            imageView.image = SwingFXUtils.toFXImage(EditedImage.getBufferedImage(), null)
        }

        @JvmStatic
        fun setImage(img : MarvinImage) {
            imageView.image = SwingFXUtils.toFXImage(img.bufferedImageNoAlpha, null)
        }

        @JvmStatic
        fun showEditorScene(button: Button) {
            val root = FXMLLoader.load<Parent>(javaClass.getResource("../scenes/editor.fxml"))
            val scene = Scene(root, 942.0, 700.0)

            val stage = button.scene.window as Stage
            stage.scene = scene
            stage.minHeight = 700.0
            stage.minWidth = 942.0
            stage.maxHeight = Double.MAX_VALUE
            stage.maxWidth = Double.MAX_VALUE
        }

        @JvmStatic
        fun initializeTooltips() {
            var tooltip = Tooltip("Remove the noise")
            noiseButton.tooltip = tooltip
            tooltip = Tooltip("RGB correction")
            colorButton.tooltip = tooltip
            tooltip = Tooltip("Black and white filter")
            bwButton.tooltip = tooltip
            tooltip = Tooltip("Brightness and contrast")
            brightnessButton.tooltip = tooltip
            tooltip = Tooltip("Auto correction")
            autoButton.tooltip = tooltip
            tooltip = Tooltip("Save the image to...")
            saveButton.tooltip = tooltip
        }
    }
}