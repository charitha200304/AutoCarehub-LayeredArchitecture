package org.example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.example.bo.BOFactory;
import org.example.bo.custom.*;
import org.example.dto.Inventory_itemDTO;
import org.example.dto.VehicleDTO;
import org.example.entity.Inventory_item;
import org.example.entity.Point_System;
import org.example.entity.Service;
import org.example.entity.Vehicle;
import org.example.view.tdm.ItemTm;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Service_formController {

    public JFXButton btnAddToCart;
    public JFXComboBox<String> cmbItemId;
    public Label lblItemName;
    public Label lblNetTotal;
    public Label lblItemQty;
    public Label lblItemPrice;
    public TextField txtQty;
    public Label lblDate;
    public Label lblModel;
    public Label lblType;
    public Label lblCustomerId;
    public TextField txtServiceId;
    public JFXComboBox cmbVehicleId;
    public Label lblDiscount;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colVehicleModel;

    @FXML
    private TableColumn<?, ?> colVehicleType;

    @FXML
    private TableColumn<?, ?> colVehicleid;

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private TableView<ItemTm> tblVehicle;

    private ObservableList<ItemTm> observableList = FXCollections.observableArrayList();
    private double fullTotal=0;

    ServiceBO serviceBO  = (ServiceBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Service);
    VehicleBO vehicleBO  = (VehicleBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Vehicle);
    Inventory_itemBO inventory_itemBO  = (Inventory_itemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Inventory);
    Point_SystemBO point_systemBO  = (Point_SystemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Point_Details);
    Place_orderBo place_orderBo  = (Place_orderBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Place_order);


    public void initialize() {
        setCellDataFactory();
        setComboBoxValues();
        setVehicleId();
        setServiceId();
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void setServiceId() {
        String id = serviceBO.fintLastServiceId();
        if (id!=null){
            int lastId = Integer.parseInt(id.substring(1));
            String nextId="S00"+(lastId+1);
            txtServiceId.setText(nextId);
        }else {
            txtServiceId.setText("S001");
        }

    }

    private void setVehicleId() {
        try {
            ArrayList<VehicleDTO> all = vehicleBO.getAllVehicle();
            ArrayList<String> allId = new ArrayList<>();
            for (VehicleDTO vehicle:all) {
                allId.add(vehicle.getV_id());
            }
            cmbVehicleId.setItems(FXCollections.observableList(allId));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setComboBoxValues() {
        try {
            ArrayList<Inventory_itemDTO> all = inventory_itemBO.getAllInventoryItem();
            ArrayList<String> allItemId = new ArrayList<>();
            for (Inventory_itemDTO item:all) {
                allItemId.add(item.getItem_id());
            }
            cmbItemId.setItems(FXCollections.observableList(allItemId));
        }  catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellDataFactory() {
        colVehicleid.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colVehicleModel.setCellValueFactory(new PropertyValueFactory<>("name"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    private ArrayList<VehicleDTO> getAllVehicle() {
        ArrayList<VehicleDTO> vehicleList = null;
        try {
            vehicleList = vehicleBO.getAllVehicle();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return vehicleList;
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String serviceId = txtServiceId.getText();
        String date = lblDate.getText();
        String vehicleId = String.valueOf(cmbVehicleId.getValue());

        Service service = new Service(serviceId, String.valueOf(fullTotal), date, vehicleId);
        boolean isSave = place_orderBo.saveService(service,observableList);
        if (isSave){
            new Alert(Alert.AlertType.CONFIRMATION,"Service Order..!").show();
            clearAll();
            setServiceId();
        }else {
            new Alert(Alert.AlertType.ERROR,"Something Wrong..!").show();
        }
    }

    private void clearAll() {
        lblDate.setText("");
        lblType.setText("");
        lblCustomerId.setText("");
        lblModel.setText("");
        lblItemPrice.setText("");
        lblNetTotal.setText("");
        lblItemPrice.setText("");
        lblItemName.setText("");
        cmbVehicleId.setValue("");
        cmbItemId.setValue("");
        observableList.clear();
        tblVehicle.setItems(observableList);
        txtQty.clear();
    }

    private void clearField(){
            lblItemPrice.setText("");
            lblItemPrice.setText("");
            lblItemName.setText("");
            cmbItemId.setValue("");
            txtQty.clear();
            lblDiscount.setText("");
    }

    @FXML
    void btnServiceHistoryOnAction(ActionEvent event) throws IOException, IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk.ijse.pos/Service_History_form.fxml"));
        Pane registePane = (Pane) fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);
    }

    public void cmbItemIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String itemId = cmbItemId.getValue();
        Inventory_item item = inventory_itemBO.searchInventoryItem(itemId);
        if (item != null) {
            lblItemName.setText(item.getDescription());
            lblItemPrice.setText(String.valueOf(item.getPrice()));
            lblItemQty.setText(String.valueOf(item.getQty_On_Hand()));
}
    }

    public void btnAddToCartOnAction() {
        String itemId = cmbItemId.getValue();
        String name = lblItemName.getText();
        double price= Double.parseDouble(lblItemPrice.getText());
        int qty= Integer.parseInt(txtQty.getText());
        double total=price*qty;

        fullTotal+=total;
        lblNetTotal.setText(String.valueOf(fullTotal));

        ItemTm itemTm = new ItemTm(itemId, name, String.valueOf(qty), String.valueOf(total));
        observableList.add(itemTm);
        tblVehicle.setItems(observableList);
        clearField();
    }

    public void cmbVehicleId(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Vehicle vehicle = vehicleBO.searchVehicle(String.valueOf(cmbVehicleId.getValue()));
       if (vehicle!=null){
           lblModel.setText(vehicle.getModel());
           lblType.setText(vehicle.getType());
           lblCustomerId.setText(vehicle.getCus_id());

           Point_System search = point_systemBO.searchPoint(vehicle.getCus_id());
           if (search!=null){
               if (Integer.parseInt(search.getTotal_point())>=1000){
                   lblDiscount.setText("Free service");
               }else {
                   lblDiscount.setText("");
               }
           }else {
               lblDiscount.setText("");
           }
       }
    }
}


