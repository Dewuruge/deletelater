package lk.ijse.depp11.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        StackPane root = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        Scene mainScene = new Scene(root);

        primaryStage.setScene(mainScene);

        primaryStage.setTitle("Cafe Shop Management System");

        primaryStage.setResizable(false);


        primaryStage.centerOnScreen();
        primaryStage.show();
//        primaryStage.setResizable(false);
//        primaryStage.setAlwaysOnTop(true);
    }

    }

