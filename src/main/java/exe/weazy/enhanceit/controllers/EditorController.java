package exe.weazy.enhanceit.controllers;

import exe.weazy.enhanceit.EditedImage;
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
        edited = Algorithms.removeNoise(EditedImage.copy());

        EditedImage.setImage(edited);
        GUI.setImage();
    }

    public void blackAndWhiteButtonClick() {
        edited = Algorithms.blackAndWhite(EditedImage.copy());

        EditedImage.setImage(edited);
        GUI.setImage();
    }

    public void colorFilterButtonClick() {
        edited = Algorithms.colorFilter(edited, deltaR, deltaG, deltaB);

        GUI.setImage(edited);
    }

    public void brightnessAndContrastButtonClick() {
        edited = Algorithms.brightnessAndContrast(edited, deltaBrightness, deltaContrast);

        GUI.setImage(edited);
    }

    public void saveButtonClick() {
        FileChooser fileChooser = GUI.getFileChooser();

        File file = fileChooser.showSaveDialog(GUI.stage);

        MarvinImageIO.saveImage(EditedImage.getMarvinImage(), file.getPath());
    }

    public void backButtonClick() {
        var result = GUI.checkAlert();

        if (result.equals(ButtonType.YES)) {
            saveButtonClick();
            EditedImage.eraseImage();
            GUI.showWelcomeScene();
        }
        else if (result.equals(ButtonType.NO)) {
            EditedImage.eraseImage();
            GUI.showWelcomeScene();
        }
    }

    public void autoButtonClick() {
        autoEdit();
    }




    public void updateDeltaR() {
        deltaR = (int)sliderR.getValue();
        colorFilterButtonClick();
    }

    public void updateDeltaG() {
        deltaG = (int)sliderG.getValue();
        colorFilterButtonClick();
    }

    public void updateDeltaB() {
        deltaB = (int)sliderB.getValue();
        colorFilterButtonClick();
    }

    public void applyRGB() {
        EditedImage.setImage(edited);
        GUI.disableSlidersRGB();
        isColorFilter = false;
    }

    public void applyBC() {
        EditedImage.setImage(edited);
        GUI.disableSlidersBC();
        isBrightnessAndContrast = false;
    }

    public void colorFilterBegin() {
        if (!isBrightnessAndContrast) {
            GUI.enableSlidersRGB();
            edited = EditedImage.copy();
            isColorFilter = true;
        }
    }

    public void brightnessAndContrastBegin() {
        if (!isColorFilter) {
            GUI.enableSlidersBC();
            edited = EditedImage.copy();
            isBrightnessAndContrast = true;
        }
    }


    public void autoEdit() {

        if (Algorithms.isDarkImage(EditedImage.getMarvinImage())) {
            deltaBrightness += 10;
            deltaContrast += 10;
        }
        else {
            deltaBrightness -= 10;
            deltaContrast -= 10;
        }

        edited = Algorithms.brightnessAndContrast(EditedImage.copy(), deltaBrightness, deltaContrast);

        deltaR -= 4;
        deltaG += 2;
        deltaB += 3;

        EditedImage.setImage(edited);

        edited = EditedImage.copy();
        edited = Algorithms.colorFilter(EditedImage.copy(), deltaR, deltaG, deltaB);

        EditedImage.setImage(edited);

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
