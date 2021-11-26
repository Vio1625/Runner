// Runner BEC Violaine 2G1TD1TP2

import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

public class Main extends Application{

    public void start(Stage primaryStage){

        primaryStage.setTitle("Runner");
        primaryStage.setResizable(false);
        Group root = new Group();
        GameScene scene = new GameScene(root);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
    public static void main(String[] args) {
        launch(args);
    }
}

