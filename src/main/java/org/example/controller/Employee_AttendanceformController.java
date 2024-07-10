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
import org.example.bo.custom.EmployeeAttendanceBO;
import org.example.bo.custom.EmployeeBO;
import org.example.dto.EmployeeAttendanceDTO;
import org.example.entity.EmployeeAttendance;
import org.example.view.tdm.EmployeeAttendanceTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Employee_AttendanceformController {

    @FXML
    private JFXButton Clearbtn;

    @FXML
    private JFXButton Deletebtn;

    @FXML
    private JFXButton Savebtn;

    @FXML
    private JFXButton Updatebtn;

    @FXML
    private JFXButton backBtnOnAction;

    @FXML
    private JFXComboBox<String> cmbEmployeeId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colInTime;

    @FXML
    private TableColumn<?, ?> colOutTime;

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private TableView<EmployeeAttendanceTm> tblEmployeeAttendance;

    @FXML
    private TextField txtAttendanceIId;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtInTime;

    @FXML
    private TextField txtOutTime;
    private List<EmployeeAttendanceDTO> employeeAttendanceList = new ArrayList<>();
    EmployeeBO employeeBO  = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Employee);
    EmployeeAttendanceBO employeeAttendanceBO  = (EmployeeAttendanceBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Employee_Attendance);
    public void initialize() {
        this.employeeAttendanceList = getAllEmployeeAttendance();
        setCellDataFactory();
        loadAllEmployeeAttendance();
        getEmployeeId();
    }

    private void getEmployeeId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> IdList = employeeBO.getIds();

            for (String id : IdList) {
                obList.add(id);
            }
            cmbEmployeeId.setItems(obList);


        } catch (SQLException | ClassNotFoundException e)  {
            throw new RuntimeException(e);
        }
    }


    private void loadAllEmployeeAttendance() {
        ObservableList<EmployeeAttendanceTm> tmList = FXCollections.observableArrayList();

        for (EmployeeAttendanceDTO employeeAttendanceDTO : employeeAttendanceList) {
            EmployeeAttendanceTm employeeAttendanceTm = new EmployeeAttendanceTm(
                    employeeAttendanceDTO.getAttendance_id(),
                    employeeAttendanceDTO.getEmployee_id(),
                    employeeAttendanceDTO.getDate(),
                    employeeAttendanceDTO.getIn_time(),
                    employeeAttendanceDTO.getOut_time()

            );
            tmList.add(employeeAttendanceTm);
        }
        tblEmployeeAttendance.setItems(tmList);
        EmployeeAttendanceTm selectedItem = tblEmployeeAttendance.getSelectionModel().getSelectedItem();
    }
    private void setCellDataFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("Attendance_id"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("Employee_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colInTime.setCellValueFactory(new PropertyValueFactory<>("In_time"));
        colOutTime.setCellValueFactory(new PropertyValueFactory<>("Out_time"));
    }
    private List<EmployeeAttendanceDTO> getAllEmployeeAttendance() {
        List<EmployeeAttendanceDTO> employeeAttendanceList = null;
        try {
            employeeAttendanceList = employeeAttendanceBO.getAllEmployeeAttendance();
        }  catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return employeeAttendanceList;
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }
    private void clearFields() {
        cmbEmployeeId.setValue("");
        txtDate.getValue();
        txtInTime.setText("");
        txtOutTime.setText("");

    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String Employee_id = cmbEmployeeId.getValue().toString();

        try {
            boolean isDeleted = employeeAttendanceBO.deleteEmployeeAttendance(Employee_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String Attendance_id = txtAttendanceIId.getText();
        String Employee_id = cmbEmployeeId.getValue().toString();
        String Date = txtDate.getValue().toString();
        String In_time = txtInTime.getText();
        String Out_time = txtOutTime.getText();


        EmployeeAttendance employeeAttendance = new EmployeeAttendance(Attendance_id,Employee_id, Date, In_time,Out_time);

        try {
            boolean isSaved = employeeAttendanceBO.saveEmployeeAttendance(new EmployeeAttendanceDTO(Attendance_id,Employee_id, Date, In_time,Out_time));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Attendance saved!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String Attendance_id = txtAttendanceIId.getText();
        String Employee_id = cmbEmployeeId.getValue().toString();
        String Date = txtDate.getValue().toString();
        String In_time = txtInTime.getText();
        String Out_time = txtOutTime.getText();


        EmployeeAttendance employeeAttendance = new EmployeeAttendance(Attendance_id,Employee_id,  Date, In_time,Out_time);

        try {
            boolean isUpdated = employeeAttendanceBO.updateEmployeeAttendance(new EmployeeAttendanceDTO(Attendance_id,Employee_id,  Date, In_time,Out_time));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Attendance updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk.ijse.pos/Employee_form.fxml"));
        Pane registePane = (Pane) fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);
    }

    public void txtOutTimeKeyReleased(KeyEvent keyEvent) {
        Pattern idPattern = Pattern.compile("^[0-9 . A-Z]*$");
        if (!idPattern.matcher(txtOutTime.getText()).matches()) {
            addError(txtOutTime);
            Savebtn.setDisable(true);

        }else{
            removeError(txtOutTime);
            Savebtn.setDisable(false);
        }
    }

    private void removeError(TextField txtVehicleId) {
        txtVehicleId.setStyle("-fx-border-color: red;  -fx-background-radius: 5; -fx-background-radius: 5");
    }

    private void addError(TextField txtVehicleId) {
        txtVehicleId.setStyle("-fx-border-color: red;  -fx-background-radius: 5; -fx-background-radius: 5");
    }
    public void txtInTimeKeyReleased(KeyEvent keyEvent) {
        Pattern idPattern = Pattern.compile("^[0-9 . A-Z]*$");
        if (!idPattern.matcher(txtInTime.getText()).matches()) {
            addError(txtInTime);
            Savebtn.setDisable(true);

        }else{
            removeError(txtInTime);
            Savebtn.setDisable(false);
        }
    }

    public void txtAttendanceIdKeyReleased(KeyEvent keyEvent) {
        Pattern idPattern = Pattern.compile("(A)[0-9].{1,9}$");
        if (!idPattern.matcher(txtAttendanceIId.getText()).matches()) {
            addError(txtAttendanceIId);
            Savebtn.setDisable(true);

        }else{
            removeError(txtAttendanceIId);
            Savebtn.setDisable(false);
        }
    }
}
