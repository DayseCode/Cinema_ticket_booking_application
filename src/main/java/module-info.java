module com.example.tempwork {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    opens com.example.tempwork to javafx.fxml;
    exports com.example.tempwork;
    opens entity;
    exports entity;
}

