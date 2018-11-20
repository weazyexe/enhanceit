package enhanceit.source;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../scenes/welcome.fxml"));

        Scene scene = new Scene(root, 709, 496);
        primaryStage.setMinWidth(709);
        primaryStage.setMinHeight(496);
        primaryStage.setTitle("Enhance it - photo enhancer");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
