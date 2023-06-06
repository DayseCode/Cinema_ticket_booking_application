package com.example.tempwork;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMenuScene {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToChoiceScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ChooseRoleScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMovieListScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MovieListScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAddingMovieScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddingMovieScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToDeletingMovieScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DeletingMovieScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToDeletingCustomerScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DeletingCustomerScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToBookedTicketScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("BookedTicketScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToReturnedTicketScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ReturnedTicketScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
