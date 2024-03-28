package com.billing.management_system;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    @FXML
    private TableView<TableModel> tblList;

    @FXML
    private ComboBox<String> cmbIds;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private TextField txtTotalPrice;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtOrderQty;



    @FXML
    void txtOrderQtyAction(ActionEvent event) {
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        try {
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            int qty = Integer.parseInt(txtOrderQty.getText());
            double total = unitPrice * qty;
            txtTotalPrice.setText(Double.toString(total));
        } catch (NumberFormatException ex) {
            txtTotalPrice.setText("");
        }
    }

    @FXML
    void add(ActionEvent event) {
        updateTotalPrice();
        double total = Double.parseDouble(txtTotalPrice.getText());
        tblList.getItems().add(new TableModel(txtName.getText(), Integer.parseInt(txtQty.getText()), Double.parseDouble(txtUnitPrice.getText()), total));
    }

    @FXML
    void placeOrder(ActionEvent event) {
        // Implement order placement logic here
    }

    @FXML
    void selectItem(ActionEvent event) {
        String selectedItem = cmbIds.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Itemm item = getItem(selectedItem);
            if (item != null) {
                txtQty.setText(String.valueOf(item.getQty()));
                txtName.setText(item.getName());
                txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
            }
        }
    }






    private ArrayList<String> loadItemIds() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/billing_management_system", "root", "sasa123@");
            PreparedStatement stm = connection.prepareStatement("SELECT item_id FROM items");
            ResultSet rst = stm.executeQuery();

            ArrayList<String> ids = new ArrayList<>();
            while (rst.next()) {
                ids.add(rst.getString(1));
            }
            return ids;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private Itemm getItem(String id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/billing_management_system", "root", "sasa123@");
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM items WHERE item_id = ?");
            stm.setObject(1, id);
            ResultSet rst = stm.executeQuery();

            if (rst.next()) {
                Itemm item = new Itemm();
                item.setIid(rst.getString(1));
                item.setName(rst.getString(2));
                item.setQty(rst.getInt(4));
                item.setUnitPrice(rst.getDouble(3));
                return item;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> itemIds = loadItemIds();
        cmbIds.setItems(FXCollections.observableArrayList(itemIds));
        initializeTableColumns();
    }

    private void initializeTableColumns() {
        tblList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("total"));
    }


}
