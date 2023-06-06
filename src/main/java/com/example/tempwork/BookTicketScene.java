package com.example.tempwork;

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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookTicketScene implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private boolean[][] seatStatus;
    public TextArea ticketTextArea;
    public Button bookTicketButton;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    EntityManager em = emf.createEntityManager();

    public void switchToUserMenuScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("UserMenuScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCustomerDataEntryScene (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("CustomerDataEntryScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void readSeatStatus() {
        // Отримання списку об'єктів TicketEntity з бази даних
        Query query = em.createQuery("SELECT t FROM TicketEntity t");
        List<TicketEntity> ticketList = query.getResultList();

        // Ініціалізація булевого масиву seatStatus
        seatStatus = new boolean[9][8];

        // Проходження по списку квитків та встановлення значень в булевому масиві seatStatus
        for (TicketEntity ticket : ticketList) {
            int row = ticket.getSeatRow();
            int place = ticket.getSeatPlace();
            boolean isTicketReturned = ticket.getTicketReturn();

            // Якщо квиток не повернутий, місце вважається зайнятим
            seatStatus[row - 1][place - 1] = !isTicketReturned;
        }

        // Виведення булевого масиву seatStatus у вигляді матриці в TextArea
        ticketTextArea.clear();
        ticketTextArea.appendText("\t\t# |\t1\t2\t3\t4\t5\t6\t7\t8\n" +
                "\t\t--|--------------------------------------\n" );
        for (int i = 0; i < 9; i++) {
            ticketTextArea.appendText("\t\t" + (i+1) + " |\t");
            for (int j = 0; j < 8; j++) {
                if (seatStatus[i][j]) {
                    ticketTextArea.appendText("X\t"); // Зайняте місце
                } else {
                    ticketTextArea.appendText("O\t"); // Вільне місце
                }
            }
            if (i != 8){ticketTextArea.appendText("\n");}
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        readSeatStatus();
    }
}
