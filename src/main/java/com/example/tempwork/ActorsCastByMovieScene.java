package com.example.tempwork;


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

public class ActorsCastByMovieScene implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public ChoiceBox<String> actorsCastChoiceBox;
    public TextArea actorsCastTextArea;

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

    public void getActorsCastByMovie(){
        String selectedMovie = actorsCastChoiceBox.getValue();

        // Очищення TextArea перед виведенням фільмів для нового жанру
        actorsCastTextArea.clear();

        // Виконання запиту для отримання списку об'єктів MoviesEntity за вибраним жанром
        Query query = em.createQuery("SELECT m.actorsCast FROM MoviesEntity m WHERE m.movieName = :movie");
        query.setParameter("movie", selectedMovie);
        List<String> actorsCastList = query.getResultList();

        // Виведення результатів або виконання інших дій зі списком фільмів
        for (String actorsCast : actorsCastList) {
            String[] actors = actorsCast.split(","); // Розділення рядка акторів по комі
            actorsCastTextArea.appendText("Actors cast:" + System.lineSeparator());
            for (String actor : actors) {
                actorsCastTextArea.appendText(actor.trim() + System.lineSeparator()); // Додавання актора на окремому рядку
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Query query = em.createQuery("SELECT m.movieName FROM MoviesEntity m");
        List<String> movies = query.getResultList();

        actorsCastChoiceBox.getItems().addAll(movies);

        // Додавання слухача до властивості valueProperty() об'єкта movieGenreChoiceBox
        actorsCastChoiceBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            getActorsCastByMovie();
        });
    }
}
