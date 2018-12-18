package exe.weazy.enhanceit.controllers;

import exe.weazy.enhanceit.GUI;
import exe.weazy.enhanceit.algorithms.Algorithms;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;

import java.io.File;


public class EditorController {

    public static MarvinImage image;

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

    @FXML
    Button noiseButton, colorButton, bwButton, brightnessButton, autoButton, saveButton;

    private static int deltaR, deltaG, deltaB;
    private static MarvinImage edited;
    private static int deltaBrightness, deltaContrast;
    private boolean isColorFilter = false, isBrightnessAndContrast = false;

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
        GUI.noiseButton = noiseButton;
        GUI.colorButton = colorButton;
        GUI.bwButton = bwButton;
        GUI.brightnessButton = brightnessButton;
        GUI.autoButton = autoButton;
        GUI.saveButton = saveButton;


        GUI.disableSlidersRGB();
        GUI.disableSlidersBC();
        GUI.setImage();
        GUI.initializeTooltips();

        initializeCloseHandler();
    }

    public void removeNoiseButtonClick() {
        edited = Algorithms.removeNoise(new MarvinImage(image.getBufferedImageNoAlpha()));

        image = new MarvinImage(edited.getBufferedImageNoAlpha());
        GUI.setImage();
    }

    public void blackAndWhiteButtonClick() {
        edited = Algorithms.blackAndWhite(new MarvinImage(image.getBufferedImageNoAlpha()));

        image = new MarvinImage(edited.getBufferedImageNoAlpha());
        GUI.setImage();
    }

    public void colorFilterSliderChange() {
        edited = Algorithms.colorFilter(new MarvinImage(image.getBufferedImageNoAlpha()), deltaR, deltaG, deltaB);

        GUI.setImage(edited);
    }

    public void brightnessAndContrastSliderChange() {
        edited = Algorithms.brightnessAndContrast(new MarvinImage(image.getBufferedImageNoAlpha()), deltaBrightness, deltaContrast);

        GUI.setImage(edited);
    }

    public void saveButtonClick() {
        FileChooser fileChooser = GUI.getFileChooser();

        File file = fileChooser.showSaveDialog(GUI.stage);

        MarvinImageIO.saveImage(image, file.getPath());
    }

    public void backButtonClick() {
        var result = GUI.checkAlert();

        if (result.equals(ButtonType.YES)) {
            saveButtonClick();
            image = null;
            GUI.showWelcomeScene();
        }
        else if (result.equals(ButtonType.NO)) {
            image = null;
            GUI.showWelcomeScene();
        }
    }

    public void autoButtonClick() {
        autoEdit();
    }


    public void updateDeltaBrightness() {
        deltaBrightness = (int)brightnessSlider.getValue();
        brightnessAndContrastSliderChange();
    }

    public void updateDeltaContrast() {
        deltaContrast = (int)contrastSlider.getValue();
        brightnessAndContrastSliderChange();
    }

    public void updateDeltaR() {
        deltaR = (int)sliderR.getValue();
        colorFilterSliderChange();
    }

    public void updateDeltaG() {
        deltaG = (int)sliderG.getValue();
        colorFilterSliderChange();
    }

    public void updateDeltaB() {
        deltaB = (int)sliderB.getValue();
        colorFilterSliderChange();
    }


    public void applyRGB() {
        image = new MarvinImage(edited.getBufferedImageNoAlpha());
        GUI.disableSlidersRGB();
        isColorFilter = false;
    }

    public void applyBC() {
        image = new MarvinImage(edited.getBufferedImageNoAlpha());
        GUI.disableSlidersBC();
        isBrightnessAndContrast = false;
    }

    public void colorFilterBegin() {
        if (!isBrightnessAndContrast) {
            GUI.enableSlidersRGB();
            edited = new MarvinImage(image.getBufferedImageNoAlpha());
            isColorFilter = true;
        }
    }

    public void brightnessAndContrastBegin() {
        if (!isColorFilter) {
            GUI.enableSlidersBC();
            edited = new MarvinImage(image.getBufferedImageNoAlpha());
            isBrightnessAndContrast = true;
        }
    }

    public void autoEdit() {
        edited = new MarvinImage(image.getBufferedImageNoAlpha());

        if (Algorithms.isDarkImage(image)) {
            deltaBrightness += 10;
            deltaContrast += 10;
        }
        else {
            deltaBrightness -= 10;
            deltaContrast -= 10;
        }

        edited = Algorithms.brightnessAndContrast(edited, deltaBrightness, deltaContrast);

        deltaR -= 4;
        deltaG += 2;
        deltaB += 3;

        image = new MarvinImage(edited.getBufferedImageNoAlpha());

        edited = new MarvinImage(image.getBufferedImageNoAlpha());
        edited = Algorithms.colorFilter(edited, deltaR, deltaG, deltaB);

        image = new MarvinImage(edited.getBufferedImageNoAlpha());

        GUI.setImage(edited);
    }

    public void initializeCloseHandler() {
        GUI.stage.setOnCloseRequest(event -> {
            var result = GUI.checkAlert();

            if (result.equals(ButtonType.YES)) {
                saveButtonClick();
                System.exit(0);
            }
            else if (result.equals(ButtonType.NO)) {
                System.exit(0);
            }
        });
    }
}
