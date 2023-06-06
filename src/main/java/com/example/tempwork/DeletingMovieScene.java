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
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DeletingMovieScene implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public ChoiceBox<String> movieListChoiceBox;

    public void switchToAdminMenuScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminMenuScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void deleteMovie(ActionEvent event) throws IOException {
        String selectedMovie = movieListChoiceBox.getValue();

        if (selectedMovie == null) {
            showAlert("Error", "Please select a movie to delete.");
            return;
        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Отримання фільму за назвою
        String queryStr = "SELECT m FROM MoviesEntity m WHERE m.movieName = :movieName";
        MoviesEntity movie = em.createQuery(queryStr, MoviesEntity.class)
                .setParameter("movieName", selectedMovie)
                .getSingleResult();

        // Видалення фільму з бази даних
        em.remove(movie);

        em.getTransaction().commit();
        em.close();
        emf.close();

        showAlert("Success", "The movie '" + selectedMovie + "' has been deleted.");
        movieListChoiceBox.getItems().remove(selectedMovie);
        movieListChoiceBox.getSelectionModel().clearSelection();

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

    private void movieListToChoiceBox() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        // Отримання списку назв фільмів з бази даних
        Query query = em.createQuery("SELECT m.movieName FROM MoviesEntity m");
        List<String> movieList = query.getResultList();

        // Очищення та заповнення ChoiceBox
        movieListChoiceBox.getItems().clear();
        movieListChoiceBox.getItems().addAll(movieList);

        em.close();
        emf.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movieListToChoiceBox();
    }
}
