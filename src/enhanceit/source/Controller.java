package enhanceit.source;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import marvin.image.MarvinImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Controller {

    @FXML
    Button loadImageButton;

    FileChooser fileChooser = new FileChooser();

    @FXML
    public void openImage() {
        try {
            // choose image to edit
            File file = fileChooser.showOpenDialog(loadImageButton.getScene().getWindow());

            // image as singleton
            EditedImage.initializeImage(file.getPath());

            // show editor form
            GUI.showEditorScene(loadImageButton);
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getClass().toString(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    public void closeApp() {
        System.exit(0);
    }

}
