package org.example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.custom.CustomerBO;
import org.example.bo.custom.Point_SystemBO;
import org.example.bo.custom.Service_HistoryBO;
import org.example.dto.Point_SystemDTO;
import org.example.entity.Customer;
import org.example.entity.Point_System;
import org.example.view.tdm.PointSystemTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Point_DetailsController {

    @FXML
    public TextField txtContact;
    @FXML
    public TextField txtDiscount;
    @FXML
    public Label lblDiscountPoint;

    @FXML
    public AnchorPane paneHolder;
    @FXML
    private JFXButton Clearbtn;

    @FXML
    private JFXButton Deletebtn;

    @FXML
    private JFXButton Savebtn;

    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private JFXComboBox<String> cmbServiceId;

    @FXML
    private TextField TxtTotalPoint;

    @FXML
    private JFXButton Updatebtn;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colId;


    @FXML
    private TableColumn<?, ?> colServiceHistoryId;

    @FXML
    private TableView<PointSystemTm> colTablePontDetail;

    @FXML
    private TableColumn<?, ?> colTotalPoint;

    @FXML
    private TextField txtId;

    @FXML
    private Label lblCustomerId;

    @FXML
    private Label lblServiceHistoryId;

    private List<Point_SystemDTO> point_systemList = new ArrayList<>();

    Service_HistoryBO service_historyBO  = (Service_HistoryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Service_History);
    CustomerBO customerBO  = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Customer);
    Point_SystemBO point_systemBO  = (Point_SystemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Point_Details);

    public void initialize() {
        this.point_systemList = getAllPointSystem();
        setCellDataFactory();
        loadAllPointSystem();
        getCustomerId();
        getServiceHistoryId();
    }

    private void getServiceHistoryId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> IdList = service_historyBO.getIds();

            for (String id : IdList) {
                obList.add(id);
            }
            cmbServiceId.setItems(obList);


        } catch (SQLException | ClassNotFoundException e)  {
            throw new RuntimeException(e);
        }
    }

    private void getCustomerId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> IdList = customerBO.getIds();

            for (String id : IdList) {
                obList.add(String.valueOf(id));
            }
            cmbCustomerId.setItems(obList);


        } catch (SQLException | ClassNotFoundException e)  {
            throw new RuntimeException(e);
        }
    }
    private void loadAllPointSystem() {
        ObservableList<PointSystemTm> tmList = FXCollections.observableArrayList();

        for (Point_SystemDTO point_systemDTO : point_systemList) {
            PointSystemTm pointSystemTm = new PointSystemTm(

                    point_systemDTO.getPoint_id(),
                    point_systemDTO.getTotal_point(),
                    point_systemDTO.getCus_id(),
                    point_systemDTO.getSh_id()


            );
            tmList.add(pointSystemTm);

        }
        colTablePontDetail.setItems(tmList);
        PointSystemTm selectedItem = colTablePontDetail.getSelectionModel().getSelectedItem();
    }
    private void setCellDataFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("Point_id"));
        colTotalPoint.setCellValueFactory(new PropertyValueFactory<>("Total_point"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("Cus_id"));
        colServiceHistoryId.setCellValueFactory(new PropertyValueFactory<>("SH_id"));

    }
    private List<Point_SystemDTO> getAllPointSystem() {
        List<Point_SystemDTO> pointSystemList = null;
        try {
            pointSystemList = point_systemBO.getAllPointSystem();
        } catch (SQLException | ClassNotFoundException e)  {
            throw new RuntimeException(e);
        }
        return pointSystemList;
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }
    private void clearFields() {
        txtId.setText("");
        TxtTotalPoint.setText("");
        cmbCustomerId.setValue("");
        cmbCustomerId.setValue("");
        lblDiscountPoint.setText("");
        txtDiscount.clear();
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String point_system = txtId.getText();

        try {
            boolean isDeleted = point_systemBO.deletePoint(point_system);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e)  {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        String Item_id = txtId.getText();
        String Total_point = TxtTotalPoint.getText();
        String Cus_id = cmbCustomerId.getValue().toString();
        String Service_id = cmbServiceId.getValue().toString();


        Point_System point_system = new Point_System(Item_id, Total_point, Cus_id, Service_id);

        try {
            boolean isSaved = point_systemBO.savePoint(new Point_SystemDTO(Item_id, Total_point, Cus_id, Service_id));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Point System saved!").show();
            }
        }  catch (SQLException | ClassNotFoundException e)  {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String Item_id = txtId.getText();
        String Total_point = TxtTotalPoint.getText();
        String Cus_id = cmbCustomerId.getValue().toString();
        String Service_id = cmbServiceId.getValue().toString();


        Point_System point_system = new Point_System(Item_id, Total_point, Cus_id, Service_id);

        try {
            boolean isUpdated = point_systemBO.updatePoint(new Point_SystemDTO(Item_id, Total_point, Cus_id, Service_id));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Point updated!").show();
            }
        }  catch (SQLException | ClassNotFoundException e)  {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void txtPointIdKeyReleased(KeyEvent keyEvent) {
        Pattern idPattern = Pattern.compile("(P)[0-9].{1,9}$");
        if (!idPattern.matcher(txtId.getText()).matches()) {
            addError(txtId);
            Savebtn.setDisable(true);
        }else{
            removeError(txtId);
            Savebtn.setDisable(false);
        }
    }

    public void txtTotalPointKeyReleased(KeyEvent keyEvent) {
        Pattern idPattern = Pattern.compile("^[0-9]*$");
        if (!idPattern.matcher(TxtTotalPoint.getText()).matches()) {
            addError(TxtTotalPoint);
            Savebtn.setDisable(true);

        }else{
            removeError(TxtTotalPoint);
            Savebtn.setDisable(false);
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer customer = customerBO.searchCustomer(txtContact.getText());
        if (customer!=null){
            Point_System value = point_systemBO.searchPoint(customer.getCus_id());
            if (value!=null){
                txtId.setText(value.getPoint_id());
                cmbCustomerId.setValue(value.getCus_id());
                cmbServiceId.setValue(value.getSh_id());
                TxtTotalPoint.setText(value.getTotal_point());

                Point_System search = point_systemBO.searchPoint(customer.getCus_id());
                if (search!=null){
                    txtDiscount.setStyle("-fx-background-color: red");
                    lblDiscountPoint.setText("DisCount : point = "+search.getTotal_point());
                }
            }
        }
    }
    private void removeError(TextField txtVehicleId) {
        txtVehicleId.setStyle("-fx-border-color: red;  -fx-background-radius: 5; -fx-background-radius: 5");
    }

    private void addError(TextField txtVehicleId) {
        txtVehicleId.setStyle("-fx-border-color: red;  -fx-background-radius: 5; -fx-background-radius: 5");
    }

    public void txtCustomerConactReleased(KeyEvent keyEvent) {
    }
}



