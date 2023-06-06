package com.example.tempwork;

import entity.MoviesEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MovieListScene implements Initializable {

    public TextArea movieListTextArea;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Створення фабрики сутностей
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

        // Отримання менеджера сутностей
        EntityManager em = emf.createEntityManager();

        try {
            // Виконання запиту для отримання списку об'єктів MoviesEntity
            Query query = em.createQuery("SELECT m FROM MoviesEntity m");
            List<MoviesEntity> moviesList = query.getResultList();

            // Виведення результатів
            for (MoviesEntity movie : moviesList) {
                movieListTextArea.appendText(movie.getMovieId() + ". " + movie.getMovieName() + "\n" +
                        "Genre: " + movie.getMovieGenre() + "\n" +
                        "Age: " + movie.getAgelimit() + "+\n" +
                        "Date: " + movie.getDateMovie() + "\n" +
                        "Actors cast:\n" + movie.getActorsCast() + "\n" +
                        "Ticket price: " + movie.getTicketPrice());
                movieListTextArea.appendText("\n-------------------------------------\n");
            }
        } finally {
            // Закриття EntityManager та EntityManagerFactory
            em.close();
            emf.close();
        }
    }
}
