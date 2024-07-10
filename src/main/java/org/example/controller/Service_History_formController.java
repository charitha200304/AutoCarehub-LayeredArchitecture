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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.example.bo.BOFactory;
import org.example.bo.custom.Service_HistoryBO;
import org.example.bo.custom.VehicleBO;
import org.example.dto.Service_HistoryDTO;
import org.example.entity.Service_History;
import org.example.entity.Vehicle;
import org.example.view.tdm.Service_HistoryTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class Service_History_formController {

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
    private Label VehicleIdlbl;
    @FXML
    private JFXComboBox<String> cmbVehicleId;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colVehicleId;

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private TableView<Service_HistoryTm> tblServiceHistory;
    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtId;
    private List<Service_HistoryDTO> service_historyList = new ArrayList<>();

    VehicleBO vehicleBO  = (VehicleBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Vehicle);
    Service_HistoryBO service_historyBO  = (Service_HistoryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Service_History);

    public void initialize() throws SQLException, ClassNotFoundException {
        this.service_historyList = getAllServiceHistory();
        setCellDataFactory();
        loadAllServiceHistory();
        getVehicleId();
    }

    private void getVehicleId() throws SQLException, ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();

        List<String> IdList = vehicleBO.getIds();

        for (String id : IdList) {
            obList.add(String.valueOf(id));
        }
        cmbVehicleId.setItems(obList);


    }

    private void loadAllServiceHistory() {
        ObservableList<Service_HistoryTm> tmList = FXCollections.observableArrayList();

        for (Service_HistoryDTO service_historyDTO :service_historyList) {
            Service_HistoryTm service_historyTm = new Service_HistoryTm(

                    service_historyDTO.getSH_id(),
                    service_historyDTO.getDescription(),
                    service_historyDTO.getV_id()


            );
            tmList.add(service_historyTm);

        }

        tblServiceHistory.setItems(tmList);
        Service_HistoryTm selectedItem = tblServiceHistory.getSelectionModel().getSelectedItem();
    }

    private List<Service_HistoryDTO> getAllServiceHistory() {
        List<Service_HistoryDTO> service_histories = null;
        try {
            service_histories = service_historyBO.getAllServiceHistory();
        }  catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return service_histories;
    }

    private void setCellDataFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("SH_id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colVehicleId.setCellValueFactory(new PropertyValueFactory<>("V_id"));
    }

    @FXML
    void BackBtnOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk.ijse.pos/Service_form.fxml"));
        Pane registePane = (Pane) fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }
    private void clearFields() {
        txtId.setText("");
        txtDescription.setText("");
        cmbVehicleId.getValue();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String SH_id = txtId.getText();

        try {
            boolean isDeleted = service_historyBO.deleteServiceHistory(SH_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        }  catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String SH_id = txtId.getText();
        String Description = txtDescription.getText();
        String V_id = cmbVehicleId.getValue().toString();


        Service_History service_history = new Service_History(SH_id, Description, V_id);

        try {
            boolean isSaved = service_historyBO.saveServiceHistory(new Service_HistoryDTO(SH_id, Description, V_id));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Service History saved!").show();
            }
        }  catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String SH_id = txtId.getText();
        String Description = txtDescription.getText();
        String V_id = cmbVehicleId.getValue().toString();


        Service_History service_history = new Service_History(SH_id, Description, V_id);

        try {
            boolean isUpdated = service_historyBO.updateServiceHistory(new Service_HistoryDTO(SH_id, Description, V_id));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "service history updated!").show();
            }
        }catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void cmbVehicleIdOnAction(ActionEvent event) {
        String id = cmbVehicleId.getValue();
        try {
            Vehicle vehicle = vehicleBO.searchVehicle(id);
            if (vehicle != null) {
                VehicleIdlbl.setText(vehicle.getV_id());

            }
        } catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);}
    }



    public void txtDescriptionKeyReleased(KeyEvent keyEvent) {
        Pattern idPattern = Pattern.compile("^[a-zA-Z 0-9]*$");
        if (!idPattern.matcher(txtDescription.getText()).matches()) {
            addError(txtDescription);
            Savebtn.setDisable(true);

        }else{
            removeError(txtDescription);
            Savebtn.setDisable(false);
        }
    }


    public void txtServiceHistoryIdKeyReleased(KeyEvent keyEvent) {
        Pattern idPattern = Pattern.compile("(SH)[0-9].{1,9}$");
        if (!idPattern.matcher(txtId.getText()).matches()) {
            addError(txtId);
            Savebtn.setDisable(true);

        }else{
            removeError(txtId);
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

