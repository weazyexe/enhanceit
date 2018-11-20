package enhanceit.source;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import marvin.image.MarvinImage;
import org.marvinproject.image.restoration.noiseReduction.NoiseReduction;


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

        edited = EditedImage.copy();
    }
}
