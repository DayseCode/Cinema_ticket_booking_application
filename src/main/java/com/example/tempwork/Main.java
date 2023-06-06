package com.example.tempwork;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ChooseRoleScene.fxml"));
        Scene scene = new Scene(root, 480, 640, Color.WHEAT);
        Image icon = new Image("C:/Users/hugov/IdeaProjects/TempWork/src/MainIcon.png");

        stage.setResizable(false);
        stage.setTitle("Cinema Booking App");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }


}