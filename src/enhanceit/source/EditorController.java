package enhanceit.source;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import marvin.image.MarvinImage;
import org.marvinproject.image.restoration.noiseReduction.NoiseReduction;
import org.marvinproject.image.color.brightnessAndContrast.BrightnessAndContrast;


public class EditorController {

    @FXML
    ImageView imgView;

    @FXML
    Slider sliderR, sliderG, sliderB;

    @FXML
    Button applyButton;

    private static int deltaR, deltaG, deltaB;
    private static MarvinImage edited;
    private static int actions = 0;

    @FXML
    public void initialize() {
        GUI.sliderR = sliderR;
        GUI.sliderG = sliderG;
        GUI.sliderB = sliderB;
        GUI.applyButton = applyButton;
        GUI.imageView = imgView;
        GUI.disableSliders();
        GUI.setImage();
    }

    public void removeNoise() {
        NoiseReduction denoiser = new NoiseReduction(); // denoiser
        MarvinImage denoised = new MarvinImage(EditedImage.getBufferedImage()); // create new image

        denoiser.load();
        denoiser.process(EditedImage.getMarvinImage(), denoised);   // processing, saving to new image

        EditedImage.setImage(denoised);     // assigning

        GUI.setImage();
    }

    public void blackAndWhite() {
        MarvinImage edited = EditedImage.getMarvinImage();

        for (int y = 0; y < edited.getHeight(); y++) {
            for (int x = 0; x < edited.getWidth(); x++) {
                int r = edited.getIntComponent0(y, x);
                int g = edited.getIntComponent1(y, x);
                int b = edited.getIntComponent2(y, x);

                int mid = (int)((r + g + b) / 3);
                edited.setIntColor(y, x, edited.getAlphaComponent(y, x), mid, mid, mid);
            }
        }

        EditedImage.setImage(edited);
        GUI.setImage();
    }

    public void colorFilter() {

        edited = new MarvinImage(EditedImage.getBufferedImage());

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

    public void sharpness() {

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

    public void apply() {
        EditedImage.setImage(edited);
        GUI.disableSliders();
    }

    public void colorFilterBegin() {
        GUI.enableSliders();

        edited = new MarvinImage(EditedImage.getBufferedImage());
    }
}
