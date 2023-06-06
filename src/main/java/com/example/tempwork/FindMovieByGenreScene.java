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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FindMovieByGenreScene implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public ChoiceBox<String> movieGenreChoiceBox;
    public TextArea movieListTextArea;

    // Створення фабрики EntityManager
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    EntityManager em = emf.createEntityManager();

    public void switchToUserMenuScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("UserMenuScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void getMovieListByGenre(){
        String selectedGenre = movieGenreChoiceBox.getValue();

        // Очищення TextArea перед виведенням фільмів для нового жанру
        movieListTextArea.clear();

        // Виконання запиту для отримання списку об'єктів MoviesEntity за вибраним жанром
        Query query = em.createQuery("SELECT m FROM MoviesEntity m WHERE m.movieGenre = :genre");
        query.setParameter("genre", selectedGenre);
        List<MoviesEntity> moviesList = query.getResultList();

        // Виведення результатів або виконання інших дій зі списком фільмів
        for (MoviesEntity movie : moviesList) {
            movieListTextArea.appendText(movie.getMovieId() + ". " + movie.getMovieName() + "\n" +
                    "Genre: " + movie.getMovieGenre() + "\n" +
                    "Age: " + movie.getAgelimit() + "+\n" +
                    "Date: " + movie.getDateMovie() + "\n" +
                    "Actors cast:\n" + movie.getActorsCast() + "\n" +
                    "Ticket price: " + movie.getTicketPrice());
            movieListTextArea.appendText("\n-------------------------------------\n");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Query query = em.createQuery("SELECT DISTINCT m.movieGenre FROM MoviesEntity m");
        List<String> genres = query.getResultList();

        movieGenreChoiceBox.getItems().addAll(genres);

        // Додавання слухача до властивості valueProperty() об'єкта movieGenreChoiceBox
        movieGenreChoiceBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            getMovieListByGenre();
        });
    }

}
