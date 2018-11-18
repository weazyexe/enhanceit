package enhanceit;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;
import org.marvinproject.image.restoration.noiseReduction.NoiseReduction;

import java.awt.image.BufferedImage;

public class Controller {

    MarvinImage mImage;
    BufferedImage bImage;

    @FXML
    ImageView imgView;

    @FXML
    Button autoButton;

    @FXML
    BorderPane borderPane;

    @FXML
    public void openImage() {

        Image img;
        mImage = MarvinImageIO.loadImage("/home/weazy/Yandex.Disk/graphix/all works/avatars/beauty11.jpg");
//        BufferedImage bimg = mImage.getBufferedImage();
//        mImage.update();
//        mImage.updateColorArray();
        bImage = mImage.getBufferedImageNoAlpha();
        img = SwingFXUtils.toFXImage(bImage, null);
        imgView.setImage(img);
    }

    @FXML
    public void closeApp() {
        System.exit(0);
    }


    @FXML
    public void denoise() {
        NoiseReduction denoiser = new NoiseReduction();
        MarvinImage denoised = new MarvinImage(bImage);
        denoiser.load();
        denoiser.process(mImage, denoised);
        bImage = denoised.getBufferedImageNoAlpha();
        imgView.setImage(SwingFXUtils.toFXImage(bImage, null));
    }

}
