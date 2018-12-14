package exe.weazy.enhanceit;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {

    @FXML
    Button loadImageButton;

    FileChooser fileChooser = new FileChooser();

    @FXML
    public void openImage() {
        try {
            // choose image to edit
            FileChooser.ExtensionFilter pngExt = new FileChooser.ExtensionFilter("PNG pictures (*.png)", "*.png");
            FileChooser.ExtensionFilter jpgExt = new FileChooser.ExtensionFilter("JPG/JPEG pictures (*.jpg)", "*.jpg", "*.jpeg");
            FileChooser.ExtensionFilter bmpExt = new FileChooser.ExtensionFilter("BMP files (*.bmp)", "*.bmp");

            fileChooser.getExtensionFilters().addAll(pngExt, jpgExt, bmpExt);

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

}
