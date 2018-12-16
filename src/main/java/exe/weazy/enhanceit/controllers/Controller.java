package exe.weazy.enhanceit.controllers;

import exe.weazy.enhanceit.EditedImage;
import exe.weazy.enhanceit.GUI;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {

    @FXML
    Button loadImageButton;

    FileChooser fileChooser;

    @FXML
    public void openImage() {
//        try {
            fileChooser = GUI.getFileChooser();

            File file = fileChooser.showOpenDialog(loadImageButton.getScene().getWindow());

            // image as singleton
            EditedImage.initializeImage(file.getPath());

            // show editor form
            GUI.showEditorScene(loadImageButton);
//        }
//        catch (Exception e) {
            //Alert alert = new Alert(Alert.AlertType.ERROR, e.getClass().toString(), ButtonType.OK);
            //alert.showAndWait();
//        }
    }

}
