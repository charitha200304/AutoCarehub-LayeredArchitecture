package org.example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.example.bo.BOFactory;
import org.example.bo.custom.CustomerBO;
import org.example.bo.custom.VehicleBO;
import org.example.dto.VehicleDTO;
import org.example.entity.Vehicle;
import org.example.view.tdm.VehicleTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class Vehicle_formController {

    @FXML
    private JFXButton Clearbtn;

    @FXML
    private JFXButton Deletebtn;

    @FXML
    private JFXButton Savebtn;

    @FXML
    private JFXButton Updatebtn;

    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private JFXComboBox<String> cmbType;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colModel;

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private TableView<VehicleTm> tblVehicle;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtVehicleId;
    private List<VehicleDTO> vehicleList = new ArrayList<>();

    VehicleBO vehicleBO  = (VehicleBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Vehicle);
    CustomerBO customerBO  = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Customer);

    public void initialize() throws SQLException, ClassNotFoundException{
        this.vehicleList = getAllVehicle();
        loadAllVehicle();
        setVehicleType();
        getCustomerId();
        setCellDataFactory();

    }
    private void loadAllVehicle() {
        ObservableList<VehicleTm> tmList = FXCollections.observableArrayList();

        for (VehicleDTO vehicleDTO : vehicleList) {
            VehicleTm vehicleTm = new VehicleTm(

                    vehicleDTO.getV_id(),
                    vehicleDTO.getModel(),
                    vehicleDTO.getType(),
                    vehicleDTO.getCus_id());
            tmList.add(vehicleTm);

        }
        tblVehicle.setItems(tmList);
        VehicleTm selectedItem = tblVehicle.getSelectionModel().getSelectedItem();
    }

    private void setCellDataFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("V_id"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("Model"));
        colType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("Cus_id"));

    }

    private List<VehicleDTO> getAllVehicle() {
        List<VehicleDTO> vehicleList = null;
        try {
            vehicleList = vehicleBO.getAllVehicle();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return vehicleList;
    }
    private void setVehicleType() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("Car");
        obList.add("Bus");
        obList.add("Lorry");
        obList.add("Van");
        cmbType.setItems(obList);
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }
    private void clearFields() {
        txtVehicleId.setText("");
        txtModel.setText("");
        cmbType.setValue("");
        cmbCustomerId.setValue("");
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String V_id = txtVehicleId.getText();

        try {
            boolean isDeleted = vehicleBO.deleteVehicle(V_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        String V_id = txtVehicleId.getText();
        String Model = txtModel.getText();
        String Type = cmbType.getValue().toString();
        String Cus_id = cmbCustomerId.getValue().toString();


        Vehicle vehicle = new Vehicle(V_id, Model, Type,  Cus_id);

        try {
            boolean isSaved = vehicleBO.saveVehicle(new VehicleDTO(V_id, Model, Type,  Cus_id));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Vehicle saved!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String V_id = txtVehicleId.getText();
        String Model = txtModel.getText();
        String Type = cmbType.getValue().toString();
        String Cus_id = cmbCustomerId.getValue().toString();


        Vehicle vehicle = new Vehicle(V_id, Model, Type , Cus_id);

        try {
            boolean isUpdated = vehicleBO.updateVehicle(new VehicleDTO(V_id, Model, Type , Cus_id));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Vehicle updated!").show();
            }
        }catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    private void getCustomerId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> IdList = customerBO.getIds();

            for (String id : IdList) {
                obList.add(id);
            }
            cmbCustomerId.setItems(obList);


        } catch (SQLException | ClassNotFoundException e)  {
            throw new RuntimeException(e);
        }
    }

    public void txtVehicleIdReleased(KeyEvent keyEvent) {
        Pattern idPattern = Pattern.compile("(V)[0-9].{0,9}$");
        if (!idPattern.matcher(txtVehicleId.getText()).matches()) {
            addError(txtVehicleId);
            Savebtn.setDisable(true);

        }else{
            removeError(txtVehicleId);
            Savebtn.setDisable(false);
        }
    }

    private void removeError(TextField txtVehicleId) {
        txtVehicleId.setStyle("-fx-border-color: red;  -fx-background-radius: 5; -fx-background-radius: 5");
    }

    private void addError(TextField txtVehicleId) {
        txtVehicleId.setStyle("-fx-border-color: red;  -fx-background-radius: 5; -fx-background-radius: 5");
    }

    public void txtVehicleModelReleased(KeyEvent keyEvent) {
        Pattern idPattern = Pattern.compile("^[a-zA-Z 0-9 ]*$");
        if (!idPattern.matcher(txtModel.getText()).matches()) {
            addError(txtModel);
            Savebtn.setDisable(true);

        }else{
            removeError(txtModel);
            Savebtn.setDisable(false);
        }
    }

    public void BackBtnOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk.ijse.pos/Customer_form.fxml"));
        Pane registePane = (Pane) fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);
    }
}
