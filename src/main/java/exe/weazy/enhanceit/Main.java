package exe.weazy.enhanceit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/welcome.fxml"));

        Scene scene = new Scene(root, 709, 496);
        primaryStage.setMaxWidth(709);
        primaryStage.setMaxHeight(496);
        primaryStage.setMinWidth(709);
        primaryStage.setMinHeight(496);
        primaryStage.setTitle("Enhance it");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
