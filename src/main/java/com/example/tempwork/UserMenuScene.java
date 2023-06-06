package com.example.tempwork;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserMenuScene {

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

    public void switchToFindMovieByGenreScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("FindMovieByGenreScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToActorsCastByMovieScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ActorsCastByMovieScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToBookTicketScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("BookTicketScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToReturnTicketScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ReturnTicketScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToListFoodDrinksScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ListFoodDrinksScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToRulesListScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("RulesListScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
