package exe.weazy.enhanceit.controllers;

import exe.weazy.enhanceit.GUI;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import marvin.io.MarvinImageIO;

import java.io.File;

public class Controller {

    FileChooser fileChooser;

    @FXML
    public void openImage() {
        try {
            fileChooser = GUI.getFileChooser();

            File file = fileChooser.showOpenDialog(GUI.stage);

            EditorController.image = MarvinImageIO.loadImage(file.getPath());

            GUI.showEditorScene();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getClass().toString(), ButtonType.OK);
            alert.showAndWait();
        }
    }

}
