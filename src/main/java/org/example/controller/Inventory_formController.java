package org.example.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.custom.Inventory_itemBO;
import org.example.dto.Inventory_itemDTO;
import org.example.entity.Inventory_item;
import org.example.view.tdm.InventoryTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Inventory_formController {

    @FXML
    private JFXButton Clearbtn;

    @FXML
    private JFXButton Deletebtn;

    @FXML
    private JFXButton Savebtn;

    @FXML
    private JFXButton Updatebtn;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private TableView<InventoryTm> tblInventory;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;



    private List<Inventory_itemDTO> inventoryList = new ArrayList<>();

    Inventory_itemBO inventory_itemBO  = (Inventory_itemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Inventory);
    public void initialize() {
        this.inventoryList = getAllInventoryItem();
        setCellDataFactory();
        loadAllInventoryItem();
    }

    private void loadAllInventoryItem() {
        ObservableList<InventoryTm> tmList = FXCollections.observableArrayList();

        for (Inventory_itemDTO inventory_itemDTO : inventoryList) {
            InventoryTm inventoryTm = new InventoryTm(

                    inventory_itemDTO.getItem_id(),
                    inventory_itemDTO.getDescription(),
                    inventory_itemDTO.getPrice(),
                    inventory_itemDTO.getQty_On_Hand()


            );
            tmList.add(inventoryTm);

        }

        tblInventory.setItems(tmList);
        InventoryTm selectedItem = tblInventory.getSelectionModel().getSelectedItem();
    }

    private void setCellDataFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("Item_id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty_On_Hand"));

    }

    private List<Inventory_itemDTO> getAllInventoryItem() {
        List<Inventory_itemDTO> inventory_itemList = null;
        try {
            inventory_itemList = inventory_itemBO.getAllInventoryItem();
        } catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        return inventory_itemList;
    }
    private void clearFields() {
        txtId.setText("");
        txtQty.setText("");
        txtPrice.setText("");
        txtQty.setText("");

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String Item_id = txtId.getText();

        try {
            boolean isDeleted = inventory_itemBO.deleteInventoryItem(Item_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        }  catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String Item_id = txtId.getText();
        String Description = txtDescription.getText();
        String Price = txtPrice.getText();
        String Qty_On_Hand = txtQty.getText();


        Inventory_item inventory_item = new Inventory_item(Item_id, Description, Price, Qty_On_Hand);

        try {
            boolean isSaved = inventory_itemBO.saveInventoryItem(new Inventory_itemDTO(Item_id, Description, Price, Qty_On_Hand));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item saved!").show();
            }
        } catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String Item_id = txtId.getText();
        String Description = txtDescription.getText();
        String Price = txtPrice.getText();
        String Qty_On_Hand = txtQty.getText();


        Inventory_item inventory_item = new Inventory_item(Item_id, Description, Price, Qty_On_Hand);

        try {
            boolean isUpdated = inventory_itemBO.updateInventoryItem(new Inventory_itemDTO(Item_id, Description, Price, Qty_On_Hand));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item  updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void txtInventoryIdKeyReleased(KeyEvent keyEvent) {
        Pattern idPattern = Pattern.compile("(I)[0-9].{1,9}$");
        if (!idPattern.matcher(txtId.getText()).matches()) {
            addError(txtId);
            Savebtn.setDisable(true);

        }else{
            removeError(txtId);
            Savebtn.setDisable(false);
        }
    }

    public void txtDescriptionKeyReleased(KeyEvent keyEvent) {
        Pattern idPattern = Pattern.compile("^[a-zA-Z 0-9 ]*$");
        if (!idPattern.matcher(txtDescription.getText()).matches()) {
            addError(txtDescription);
            Savebtn.setDisable(true);

        }else{
            removeError(txtDescription);
            Savebtn.setDisable(false);
        }
    }


    public void txtPriceKeyReleased(KeyEvent keyEvent) {
        Pattern idPattern = Pattern.compile("^[0-9]*$");
        if (!idPattern.matcher(txtPrice.getText()).matches()) {
            addError(txtPrice);
            Savebtn.setDisable(true);
        }else{
            removeError(txtPrice);
            Savebtn.setDisable(false);
        }
    }

    public void txtQtyKeyReleased(KeyEvent keyEvent) {
        Pattern idPattern = Pattern.compile("^[0-9]*$");
        if (!idPattern.matcher(txtQty.getText()).matches()) {
            addError(txtQty);
            Savebtn.setDisable(true);

        }else{
            removeError(txtQty);
            Savebtn.setDisable(false);
        }
    }
    private void removeError(TextField txtVehicleId) {
        txtVehicleId.setStyle("-fx-border-color: red;  -fx-background-radius: 5; -fx-background-radius: 5");
    }

    private void addError(TextField txtVehicleId) {
        txtVehicleId.setStyle("-fx-border-color: red;  -fx-background-radius: 5; -fx-background-radius: 5");
    }
}


