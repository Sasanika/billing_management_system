package com.billing.management_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.*;

public class ItemController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private AnchorPane root3;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TableView<Item> itemTable;

    @FXML
    private TableColumn<Item, String> colItemId;

    @FXML
    private TableColumn<Item, String> colItemName;

    @FXML
    private TableColumn<Item, Integer> colItemQty;

    @FXML
    private TableColumn<Item, Double> colItemUPrice;


    private ObservableList<Item> itemsList = FXCollections.observableArrayList();

    public void initialize() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colItemUPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        itemTable.setItems(itemsList);
    }

    @FXML
    void delete(ActionEvent event) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/billing_management_system", "root", "sasa123@");


            String itemToDelete = txtCode.getText();


            PreparedStatement stn = connection.prepareStatement("DELETE FROM items WHERE item_id = ?");
            stn.setString(1, itemToDelete);

            int rowsAffected = stn.executeUpdate();

            if (rowsAffected > 0) {
                showAlert("Item deleted.");
            } else {
               showAlert("No items deleted.");
            }

            connection.close();
        } catch (ClassNotFoundException e) {
            e.getMessage();
        } catch (SQLException e) {
            e.getMessage();
        }

        clearFields();
    }


    @FXML
    void save(ActionEvent event) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/billing_management_system", "root", "sasa123@");


            PreparedStatement stn = connection.prepareStatement("INSERT INTO items (item_id, name, unit_price, quantity) VALUES (?, ?, ?, ?)");


            stn.setString(1, txtCode.getText());
            stn.setString(2, txtName.getText());
            stn.setDouble(3, Double.parseDouble(txtPrice.getText()));
            stn.setInt(4, Integer.parseInt(txtQty.getText()));


            int i = stn.executeUpdate();

            if (i > 0) {
                Item newItem = new Item(txtCode.getText(), txtName.getText(), (int) Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtQty.getText()));
                itemsList.add(newItem);
                showAlert("Data saved successfully.");
                clearFields();
            } else {
                showAlert("Data not saved. Please check your input.");
            }

            // Close the database connection
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
    }



    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Save Status");
        alert.setHeaderText(message);
        alert.show();
    }


    private void clearFields() {
        txtCode.clear();
        txtName.clear();
        txtQty.clear();
        txtPrice.clear();
    }



    @FXML
    void search(ActionEvent event) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/billing_management_system", "root", "sasa123@");

            PreparedStatement stn = connection.prepareStatement("SELECT * FROM items WHERE item_id = ?");

            stn.setObject(1, txtCode.getText());

            ResultSet rst = stn.executeQuery();

            if (rst.next()) {
                txtName.setText(rst.getString("name"));
                txtPrice.setText(rst.getString("unit_price"));
                txtQty.setText(rst.getString("quantity"));
            } else {

                System.out.println("Item not found.");
                clearFields();
            }

            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void update(ActionEvent event) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/billing_management_system", "root", "sasa123@");

            PreparedStatement stn = connection.prepareStatement("update items set name = ?,quantity = ?,unit_price=? where item_id = ?;");



            stn.setObject(1, txtName.getText());
            stn.setObject(2, txtQty.getText());
            stn.setObject(3, txtPrice.getText());
            stn.setObject(4, txtCode.getText());
            //execute query


            int i = stn.executeUpdate();

            if(i>0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update status");
                alert.setHeaderText("Data Updated");
                alert.show();

            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update status");
                alert.setHeaderText("Data does not Updated");
                alert.show();
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        clearFields();



    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(Button btnSave) {
        this.btnSave = btnSave;
    }

    public Button getBtnSearch() {
        return btnSearch;
    }

    public void setBtnSearch(Button btnSearch) {
        this.btnSearch = btnSearch;
    }

    public Button getBtnUpdate() {
        return btnUpdate;
    }

    public void setBtnUpdate(Button btnUpdate) {
        this.btnUpdate = btnUpdate;
    }

    public AnchorPane getRoot3() {
        return root3;
    }

    public void setRoot3(AnchorPane root3) {
        this.root3 = root3;
    }

    public TextField getTxtCode() {
        return txtCode;
    }

    public void setTxtCode(TextField txtCode) {
        this.txtCode = txtCode;
    }

    public TextField getTxtName() {
        return txtName;
    }

    public void setTxtName(TextField txtName) {
        this.txtName = txtName;
    }

    public TextField getTxtPrice() {
        return txtPrice;
    }

    public void setTxtPrice(TextField txtPrice) {
        this.txtPrice = txtPrice;
    }

    public TextField getTxtQty() {
        return txtQty;
    }

    public void setTxtQty(TextField txtQty) {
        this.txtQty = txtQty;
    }
}
