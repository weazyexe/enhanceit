package enhanceit;

import javafx.embed.swing.SwingFXUtils;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import marvin.gui.MarvinImagePanel;
import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Controller {

    @FXML
    ImageView imgView;

    @FXML
    Button autoButton;

    @FXML
    BorderPane borderPane;

    @FXML
    public void openImage() {

        Image img;
        MarvinImage mImage = MarvinImageIO.loadImage("C:\\Users\\weazy\\Desktop\\beauty11.jpg");
//        BufferedImage bimg = mImage.getBufferedImage();
//        mImage.update();
//        mImage.updateColorArray();
        BufferedImage bimg = mImage.getBufferedImageNoAlpha();
        img = SwingFXUtils.toFXImage(bimg, null);
        imgView.setImage(img);
    }

    @FXML
    public void closeApp() {
        System.exit(0);
    }


}
