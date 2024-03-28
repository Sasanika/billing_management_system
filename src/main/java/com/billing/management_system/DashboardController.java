package com.billing.management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {
    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnItems;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnOrders;

    @FXML
    private AnchorPane root2;



    @FXML
    void dashboard(ActionEvent event) {

    }

    @FXML
    void items(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("item-view.fxml"));
        AnchorPane itemPane = fxmlLoader.load();

        root2.getChildren().setAll(itemPane);
        Stage stage = (Stage) this.root2.getScene().getWindow();
        stage.setTitle("Orders");


    }

    @FXML
    void logout(ActionEvent event) {

        Stage stage = (Stage) this.root2.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        //created the scene


        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);

    }

    @FXML
    void orders(ActionEvent event) {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("order-view.fxml"));

        AnchorPane itemPane = null;
        try {
            itemPane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        root2.getChildren().setAll(itemPane);
        Stage stage = (Stage) this.root2.getScene().getWindow();
        stage.setTitle("Orders");

    }

}
