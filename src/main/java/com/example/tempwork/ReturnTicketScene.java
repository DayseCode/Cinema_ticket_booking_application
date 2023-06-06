package com.example.tempwork;

import entity.CustomersEntity;
import entity.TicketEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
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

public class ReturnTicketScene {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public TextField seatRowTextField;
    public TextField customerPhoneTextField;
    public TextField seatPlaceTextField;
    public DatePicker todayDatePicker;

    public void switchToUserMenuScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("UserMenuScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void returnTicket(ActionEvent event) throws IOException {
        String customerPhone = customerPhoneTextField.getText();
        int seatRow = Integer.parseInt(seatRowTextField.getText());
        int seatPlace = Integer.parseInt(seatPlaceTextField.getText());

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Пошук користувача за номером телефону
        Query customerQuery = em.createQuery("SELECT c FROM CustomersEntity c WHERE c.customerPhone = :phone");
        customerQuery.setParameter("phone", customerPhone);
        CustomersEntity customer = (CustomersEntity) customerQuery.getSingleResult();

        int customerId = customer.getCustomerId();

        // Пошук квитка за customer_id, рядом та місцем
        Query ticketQuery = em.createQuery("SELECT t FROM TicketEntity t WHERE t.customerId = :customerId " +
                "AND t.seatRow = :seatRow AND t.seatPlace = :seatPlace");
        ticketQuery.setParameter("customerId", customerId);
        ticketQuery.setParameter("seatRow", seatRow);
        ticketQuery.setParameter("seatPlace", seatPlace);
        TicketEntity ticket = (TicketEntity) ticketQuery.getSingleResult();

        // Оновлення значень квитка
        ticket.setTicketReturn(true);
        ticket.setDateOfReturn(Date.valueOf(todayDatePicker.getValue()));

        em.getTransaction().commit();
        em.close();
        emf.close();

        // Виведення повідомлення про успіх
        showAlert("Success", "Ticket returned successfully.");

        root = FXMLLoader.load(getClass().getResource("UserMenuScene.fxml"));
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
