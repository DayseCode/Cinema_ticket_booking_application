package com.example.tempwork;

import entity.CustomersEntity;
import entity.TicketEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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

public class DeletingCustomerScene implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public ChoiceBox<String> customerListChoiceBox;

    public void switchToAdminMenuScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminMenuScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void deleteCustomer(ActionEvent event) throws IOException {
        String selectedCustomer = customerListChoiceBox.getValue();
        if (selectedCustomer != null) {
            // Отримання customer_id з вибраного користувача у ChoiceBox
            int customerId = Integer.parseInt(selectedCustomer.split("\\.")[0]);

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
            EntityManager em = emf.createEntityManager();

            // Видалення всіх даних про користувача з бази даних TicketEntity за customer_id
            em.getTransaction().begin();
            em.createQuery("DELETE FROM TicketEntity t WHERE t.customerId = :customerId")
                    .setParameter("customerId", customerId)
                    .executeUpdate();
            em.getTransaction().commit();

            // Видалення користувача з бази даних CustomerEntity за customer_id
            em.getTransaction().begin();
            CustomersEntity customer = em.find(CustomersEntity.class, customerId);
            em.remove(customer);
            em.getTransaction().commit();

            em.close();
            emf.close();

            // Оновлення списку користувачів у ChoiceBox після видалення
            updateCustomerListChoiceBox();

            // Виведення повідомлення про успішне видалення користувача
            showAlert("Success", "Customer " + customer.getCustomerName() + " has been deleted.");

            root = FXMLLoader.load(getClass().getResource("AdminMenuScene.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    private void updateCustomerListChoiceBox() {
        customerListChoiceBox.getItems().clear();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        // Отримання списку всіх користувачів з бази даних CustomerEntity
        List<CustomersEntity> customers = em.createQuery("SELECT c FROM CustomersEntity c", CustomersEntity.class)
                .getResultList();

        for (CustomersEntity customer : customers) {
            int customerId = customer.getCustomerId();
            String customerName = customer.getCustomerName();
            String customerSurname = customer.getCustomerSurname();

            String customerItem = customerId + ". " + customerName + " " + customerSurname;
            customerListChoiceBox.getItems().add(customerItem);
        }

        em.close();
        emf.close();


    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateCustomerListChoiceBox();
    }
}
