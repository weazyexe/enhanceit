package enhanceit.source;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;
import org.marvinproject.image.color.brightnessAndContrast.BrightnessAndContrast;
import org.marvinproject.image.restoration.noiseReduction.NoiseReduction;

import java.io.File;


public class EditorController {

    @FXML
    ImageView imgView;

    @FXML
    Slider sliderR, sliderG, sliderB;

    @FXML
    Button applyRGBButton, applyBrightnessButton;

    @FXML
    Slider brightnessSlider, contrastSlider;

    @FXML
    Label brightnessLabel, contrastLabel;

    private static int deltaR, deltaG, deltaB;
    private static MarvinImage edited;
    private static int deltaBrightness, deltaContrast;

    @FXML
    public void initialize() {
        GUI.sliderR = sliderR;
        GUI.sliderG = sliderG;
        GUI.sliderB = sliderB;
        GUI.applyRGBButton = applyRGBButton;
        GUI.imageView = imgView;
        GUI.brightnessSlider = brightnessSlider;
        GUI.contrastSlider = contrastSlider;
        GUI.brightnessLabel = brightnessLabel;
        GUI.contrastLabel = contrastLabel;
        GUI.applyBrightnessButton = applyBrightnessButton;

        GUI.disableSlidersRGB();
        GUI.disableSlidersBC();
        GUI.setImage();
    }

    public void removeNoise() {
        NoiseReduction noiseReduction = new NoiseReduction();
        MarvinImage withoutNoiseImage = new MarvinImage(EditedImage.getBufferedImage()); // create new image

        noiseReduction.load();
        noiseReduction.process(EditedImage.getMarvinImage(), withoutNoiseImage);   // processing, saving to new image

        EditedImage.setImage(withoutNoiseImage);     // assigning

        GUI.setImage();
    }

    public void blackAndWhite() {
        MarvinImage edited = EditedImage.getMarvinImage();

        for (int y = 0; y < edited.getHeight(); y++) {
            for (int x = 0; x < edited.getWidth(); x++) {
                int r = edited.getIntComponent0(y, x);
                int g = edited.getIntComponent1(y, x);
                int b = edited.getIntComponent2(y, x);

                int mid = (r + g + b) / 3;
                edited.setIntColor(y, x, edited.getAlphaComponent(y, x), mid, mid, mid);
            }
        }

        EditedImage.setImage(edited);
        GUI.setImage();
    }

    public void colorFilter() {

        edited = EditedImage.copy();

        for (int y = 0; y < edited.getHeight(); y++) {
            for (int x = 0; x < edited.getWidth(); x++) {
                int r = edited.getIntComponent0(y, x);
                int g = edited.getIntComponent1(y, x);
                int b = edited.getIntComponent2(y, x);

                r += deltaR;
                if (r > 255) r = 255;
                if (r < 0) r = 0;

                g += deltaG;
                if (g > 255) g = 255;
                if (g < 0) g = 0;

                b += deltaB;
                if (b > 255) b = 255;
                if (b < 0) b = 0;

                edited.setIntColor(y, x, edited.getAlphaComponent(y, x), r, g, b);
            }
        }

        GUI.setImage(edited);
    }

    public void brightnessAndContrast() {
        deltaBrightness = (int)brightnessSlider.getValue();
        deltaContrast = (int)contrastSlider.getValue();

        BrightnessAndContrast bac = new BrightnessAndContrast();
        edited = EditedImage.copy();

        bac.load();
        bac.setAttribute("brightness", deltaBrightness);
        bac.setAttribute("contrast", deltaContrast);

        bac.process(EditedImage.getMarvinImage(), edited);

        GUI.setImage(edited);
    }

    public void updateDeltaR() {
        deltaR = (int)sliderR.getValue();
        colorFilter();
    }

    public void updateDeltaG() {
        deltaG = (int)sliderG.getValue();
        colorFilter();
    }

    public void updateDeltaB() {
        deltaB = (int)sliderB.getValue();
        colorFilter();
    }

    public void applyRGB() {
        EditedImage.setImage(edited);
        GUI.disableSlidersRGB();
    }

    public void applyBC() {
        EditedImage.setImage(edited);
        GUI.disableSlidersBC();
    }

    public void colorFilterBegin() {
        GUI.enableSlidersRGB();

        edited = EditedImage.copy();
    }

    public void brightnessAndContrastBegin() {
        GUI.enableSlidersBC();

        edited = EditedImage.copy();
    }

    public void save() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter pngExt = new FileChooser.ExtensionFilter("PNG pictures (*.png)", "*.png");
        FileChooser.ExtensionFilter jpgExt = new FileChooser.ExtensionFilter("JPG/JPEG pictures (*.jpg)", "*.jpg", "*.jpeg");
        FileChooser.ExtensionFilter bmpExt = new FileChooser.ExtensionFilter("BMP files (*.bmp)", "*.bmp");

        fileChooser.getExtensionFilters().addAll(pngExt, jpgExt, bmpExt);

        File file = fileChooser.showSaveDialog(applyRGBButton.getScene().getWindow());

        MarvinImageIO.saveImage(EditedImage.getMarvinImage(), file.getPath());
    }
}
