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
            File file = fileChooser.showOpenDialog(loadImageButton.getScene().getWindow());

            EditedImage.initializeImage(file.getPath());

            Parent root = FXMLLoader.load(getClass().getResource("../scenes/editor.fxml"));
            Scene scene = new Scene(root, 709, 578);

            Stage stage = (Stage)loadImageButton.getScene().getWindow();
            stage.setScene(scene);
        }
        catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    public void closeApp() {
        System.exit(0);
    }

}
