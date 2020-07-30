package imenik;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Imenik extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));

        Scene scene = new Scene(root, 500, 350);

        primaryStage.setTitle("Prijavi se");
        primaryStage.setScene(scene);

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
