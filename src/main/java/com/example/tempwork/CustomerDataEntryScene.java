package com.example.tempwork;

import entity.CustomersEntity;
import entity.MoviesEntity;
import entity.TicketEntity;
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
import javafx.scene.control.*;
import javafx.scene.shape.QuadCurve;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerDataEntryScene implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public TextField customerSurnameField;
    public TextField customerNameField;
    public TextField customerPhoneField;
    public ChoiceBox<String> movieListChoiceBox;
    public ChoiceBox<Integer> seatRowChoiceBox;
    public ChoiceBox<Integer> seatPlaceChoiceBox;
    public DatePicker bookDatePicker;
    public DialogPane ticketDialogPane;
    public Label customerNameSurLabel;
    public Label movieNameLabel;
    public Label bookDateLabel;
    public Label showDateLabel;
    public Label seatRowLabel;
    public Label seatPlaceLabel;
    public Label ticketPriceLabel;


    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    EntityManager em = emf.createEntityManager();

    public void switchToBookTicketScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("BookTicketScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void movieListToChoiceBox() {
        // Отримання списку назв фільмів з бази даних
        Query query = em.createQuery("SELECT m.movieName FROM MoviesEntity m");
        List<String> movieList = query.getResultList();

        // Додавання назв фільмів до ChoiceBox
        movieListChoiceBox.getItems().addAll(movieList);
    }

    public void confirmCustomerData(ActionEvent event) throws IOException {
        // Отримання даних з текстових полів
        String customerName = customerNameField.getText();
        String customerSurname = customerSurnameField.getText();
        String customerPhone = customerPhoneField.getText();

        // Отримання вибраних значень з ChoiceBox
        String selectedMovie = movieListChoiceBox.getValue().toString();
        int selectedSeatRow = seatRowChoiceBox.getValue();
        int selectedSeatPlace = seatPlaceChoiceBox.getValue();

        // Отримання вибраної дати
        java.sql.Date selectedDate = Date.valueOf(bookDatePicker.getValue());

        // Збереження даних в базу даних

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Створення об'єкта CustomersEntity і збереження його в базу даних
        CustomersEntity customer = new CustomersEntity();
        customer.setCustomerName(customerName);
        customer.setCustomerSurname(customerSurname);
        customer.setCustomerPhone(customerPhone);
        em.persist(customer);

        // Створення об'єкта TicketEntity і збереження його в базу даних
        TicketEntity ticket = new TicketEntity();
        ticket.setCustomerId(customer.getCustomerId()); // використовуємо id створеного замовника
        ticket.setMovieId(getMovieIdByName(selectedMovie, em)); // отримуємо id фільму за назвою
        ticket.setSeatRow(selectedSeatRow);
        ticket.setSeatPlace(selectedSeatPlace);
        ticket.setDateOfBook(selectedDate);
        ticket.setTicketReturn(false);
        em.persist(ticket);


        // Отримання даних про фільм за його id
        MoviesEntity movie = em.find(MoviesEntity.class, ticket.getMovieId());

        // Перехід до діалогового вікна з даними квитка
        ticketDialogPane.setVisible(true);
        customerNameSurLabel.setText(customerName + " " + customerSurname + ",");
        movieNameLabel.setText(selectedMovie);
        bookDateLabel.setText(selectedDate.toString());
        showDateLabel.setText(String.valueOf(movie.getDateMovie()));
        seatRowLabel.setText(Integer.toString(selectedSeatRow));
        seatPlaceLabel.setText(Integer.toString(selectedSeatPlace));
        ticketPriceLabel.setText(Double.toString(movie.getTicketPrice()) + " UAH");

        em.getTransaction().commit();
        em.close();
        emf.close();

    }

    private int getMovieIdByName(String movieName, EntityManager em) {
        // Отримання id фільму за назвою
        String query = "SELECT m FROM MoviesEntity m WHERE m.movieName = :movieName";
        MoviesEntity movie = em.createQuery(query, MoviesEntity.class)
                .setParameter("movieName", movieName)
                .getSingleResult();
        return movie.getMovieId();
    }

    public void ticketLeaveButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("UserMenuScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Додавання назві фільмів в ChoiceBox для MovieListChoiceBox
        movieListToChoiceBox();

        // Додавання цифр в ChoiceBox для seatRowChoiceBox
        for (int i = 1; i <= 9; i++) {
            seatRowChoiceBox.getItems().add(i);
        }

        // Додавання цифр в ChoiceBox для seatPlaceChoiceBox
        for (int i = 1; i <= 8; i++) {
            seatPlaceChoiceBox.getItems().add(i);
        }


    }
}
