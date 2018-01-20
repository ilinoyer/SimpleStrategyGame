package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainWindow.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Strategy Game");
        primaryStage.setMinHeight(520);
        primaryStage.setMinWidth(950);
        primaryStage.setScene(new Scene(root, 950, 520));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
