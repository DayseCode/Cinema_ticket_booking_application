package com.example.tempwork;

import entity.CustomersEntity;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookedTicketScene implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public TextArea bookedTicketTextArea;

    public void switchToAdminMenuScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminMenuScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        StringBuilder ticketInfo = new StringBuilder();

        // Отримання списку customers з бази даних
        List<CustomersEntity> customers = em.createQuery("SELECT c FROM CustomersEntity c", CustomersEntity.class)
                .getResultList();

        for (CustomersEntity customer : customers) {
            int customerId = customer.getCustomerId();
            String customerName = customer.getCustomerName();
            String customerSurname = customer.getCustomerSurname();
            String customerPhone = customer.getCustomerPhone();

            ticketInfo.append(customerId).append(". ").append(customerName).append(" ").append(customerSurname).append("\n");
            ticketInfo.append("Phone: ").append(customerPhone).append("\n");

            // Отримання бронювань для даного customer_id з бази даних
            List<TicketEntity> tickets = em.createQuery("SELECT t FROM TicketEntity t WHERE t.customerId = :customerId", TicketEntity.class)
                    .setParameter("customerId", customerId)
                    .getResultList();

            for (TicketEntity ticket : tickets) {
                int seatRow = ticket.getSeatRow();
                int seatPlace = ticket.getSeatPlace();
                String dateOfBook = ticket.getDateOfBook().toString();
                boolean ticketReturn = ticket.getTicketReturn();
                String dateOfReturn = ticket.getDateOfReturn() != null ? ticket.getDateOfReturn().toString() : "-";

                ticketInfo.append("Row: ").append(seatRow).append(", Place: ").append(seatPlace).append("\n");
                ticketInfo.append("Book date: ").append(dateOfBook).append("\n");
                ticketInfo.append("Ticket return: ").append(ticketReturn ? "YES" : "NO").append("\n");
                ticketInfo.append("Return date: ").append(dateOfReturn).append("\n");
            }

            ticketInfo.append("\n");
        }

        em.close();
        emf.close();

        bookedTicketTextArea.setText(ticketInfo.toString());
    }
}
