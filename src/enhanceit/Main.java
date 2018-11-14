package enhanceit;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import marvin.gui.MarvinImagePanel;
import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;

import javax.swing.*;
import java.awt.*;


public class Main extends Application {

    private double xOffset;
    private double yOffset;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainstage.fxml"));

//        final SwingNode node = new SwingNode();
//        createSwingContent(node);
//
//        borderPane = (BorderPane)root.lookup("#borderPane");
//        borderPane.setCenter(node);
//        root.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 10, 0.5, 0.0, 0.0);");

        Scene scene = new Scene(root, 920, 700);

        primaryStage.setTitle("Hello World");
        primaryStage.setMinWidth(920);
        primaryStage.setMinHeight(700);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

    private void createSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(() -> {
            MarvinImagePanel imagePanel = new MarvinImagePanel();
            imagePanel.setMinimumSize(new Dimension(800, 550));
            imagePanel.setMaximumSize(new Dimension(800, 550));
            //imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            MarvinImage mImage = MarvinImageIO.loadImage("C:\\Users\\weazy\\Desktop\\beauty11.jpg");
            imagePanel.setImage(mImage);


            swingNode.setContent(imagePanel);
        });
    }
}
