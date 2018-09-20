import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;

public class zegar extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("clockPanel.fxml"));
            Scene scene=new Scene(root);
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        catch(Exception e){
            e.printStackTrace();
            }
        }



    }



