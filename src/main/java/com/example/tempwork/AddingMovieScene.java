package com.example.tempwork;

import entity.MoviesEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

public class AddingMovieScene {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public TextField movieGenreTextField;
    public TextField movieNameTextField;
    public TextField ageLimitTextField;
    public DatePicker movieDatePicker;
    public TextField actorsCastTextField;
    public TextField ticketPriceTextField;

    public void switchToAdminMenuScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminMenuScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addMovie(ActionEvent event) throws IOException{
        String movieName = movieNameTextField.getText();
        String movieGenre = movieGenreTextField.getText().toLowerCase();
        int ageLimit = Integer.parseInt(ageLimitTextField.getText());
        java.sql.Date dateMovie = Date.valueOf(movieDatePicker.getValue());
        String actorsCast = actorsCastTextField.getText();
        double ticketPrice = Integer.parseInt(ticketPriceTextField.getText());

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Створення об'єкта MoviesEntity та збереження його в базу даних
        MoviesEntity movie = new MoviesEntity();
        movie.setMovieName(movieName);
        movie.setMovieGenre(movieGenre);
        movie.setAgelimit(ageLimit);
        movie.setDateMovie(dateMovie);
        movie.setActorsCast(actorsCast);
        movie.setTicketPrice(ticketPrice);
        em.persist(movie);
        em.getTransaction().commit();

        em.close();
        emf.close();

        showAlert("Successful movie adding", "Movie " + movieName + " has been added successfully.");

        // Очищення полів введення
        movieNameTextField.clear();
        movieGenreTextField.clear();
        ageLimitTextField.clear();
        movieDatePicker.setValue(null);
        actorsCastTextField.clear();
        ticketPriceTextField.clear();

        root = FXMLLoader.load(getClass().getResource("AdminMenuScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
