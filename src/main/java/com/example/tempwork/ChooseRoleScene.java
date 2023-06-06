package com.example.tempwork;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseRoleScene {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToUserMenuScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("UserMenuScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToAdminMenuScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminMenuScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
