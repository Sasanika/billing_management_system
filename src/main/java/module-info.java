module com.billing.management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.billing.management_system to javafx.fxml;
    exports com.billing.management_system;
}