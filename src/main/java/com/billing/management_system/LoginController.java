package com.billing.management_system;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {

    @FXML
    private Button btnSignIn;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;
    @FXML
    private AnchorPane root1;


    @FXML
    void signin(ActionEvent event) {
        try {


            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/billing_management_system", "root", "sasa123@");

            PreparedStatement stn = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");

            stn.setString(1, txtUsername.getText());
            stn.setString(2, txtPassword.getText());
            ResultSet rs = stn.executeQuery();



            if (rs.next()) {
                System.out.println("Sign in success");
                Stage stage = (Stage) this.root1.getScene().getWindow();

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboard-view.fxml"));
                //created the scene
                stage.setTitle("Dashboard");


                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
            } else {
                System.out.println("Sign in wrong");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        txtUsername.setText("");
        txtPassword.setText("");
    }




    public Button getBtnSignIn() {
        return btnSignIn;
    }

    public void setBtnSignIn(Button btnSignIn) {
        this.btnSignIn = btnSignIn;
    }

    public PasswordField getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(PasswordField txtPassword) {
        this.txtPassword = txtPassword;
    }

    public TextField getTxtUsername() {
        return txtUsername;
    }

    public void setTxtUsername(TextField txtUsername) {
        this.txtUsername = txtUsername;
    }



}
