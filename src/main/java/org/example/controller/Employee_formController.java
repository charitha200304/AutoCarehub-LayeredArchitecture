package org.example.controller;

import com.jfoenix.controls.JFXButton;
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
import org.example.bo.custom.EmployeeBO;
import org.example.dto.EmployeeDTO;
import org.example.entity.Employee;
import org.example.view.tdm.EmployeeTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Employee_formController{

    @FXML
    private JFXButton Clearbtn;

    @FXML
    private JFXButton Deletebtn;

    @FXML
    private JFXButton EmployeeAttendanceBtn;

    @FXML
    private JFXButton Savebtn;

    @FXML
    private JFXButton Updatebtn;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    @FXML
    private TextField txtAdress;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtName;
    private List<EmployeeDTO> employeeList = new ArrayList<>();
    @FXML
    private AnchorPane paneHolder;

    EmployeeBO employeeBO  = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Employee);

    public void initialize() {
        this.employeeList = getAllEmployee();
        setCellDataFactory();
        loadAllEmployee();
    }


    private void loadAllEmployee() {
        ObservableList<EmployeeTm> tmList = FXCollections.observableArrayList();

        for (EmployeeDTO employeeDTO : employeeList) {
            EmployeeTm employeeTm = new EmployeeTm(

                    employeeDTO.getEmployee_id(),
                    employeeDTO.getName(),
                    employeeDTO.getAddress(),
                    employeeDTO.getContact_number()


            );
            tmList.add(employeeTm);

        }
        tblEmployee.setItems(tmList);
        EmployeeTm selectedItem = tblEmployee.getSelectionModel().getSelectedItem();
    }
    private void setCellDataFactory() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("Employee_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("Contact_number"));

    }
    private List<EmployeeDTO> getAllEmployee() {
        List<EmployeeDTO> employeeList = null;
        try {
            employeeList = employeeBO.getAllEmployee();
        } catch (SQLException | ClassNotFoundException e)  {
            throw new RuntimeException(e);
        }
        return employeeList;
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }
    private void clearFields() {
        txtEmployeeId.setText("");
        txtName.setText("");
        txtAdress.setText("");
        txtContactNumber.setText("");

    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String employee_id = txtEmployeeId.getText();

        try {
            boolean isDeleted = employeeBO.deleteEmployee(employee_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e)  {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnEmployeeAttendanceOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk.ijse.pos/Employee_Attendance_form.fxml"));
        Pane registePane = (Pane) fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String Employee_id = txtEmployeeId.getText();
        String Name = txtName.getText();
        String Address = txtAdress.getText();
        String Contact_number = txtContactNumber.getText();


        Employee employee = new Employee(Employee_id, Name, Address, Contact_number);

        try {
            boolean isSaved = employeeBO.saveEmployee(new EmployeeDTO(Employee_id, Name, Address, Contact_number));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String Employee_id = txtEmployeeId.getText();
        String Name = txtName.getText();
        String Address = txtAdress.getText();
        String Contact_number = txtContactNumber.getText();


        Employee employee = new Employee(Employee_id, Name, Address, Contact_number);

        try {
            boolean isUpdated = employeeBO.updateEmployee(new EmployeeDTO(Employee_id, Name, Address, Contact_number));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee updated!").show();
            }
        }  catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void txtEmployeeIdKeyReleased(KeyEvent keyEvent) {
        Pattern idPattern = Pattern.compile("(E)[0-9].{1,9}$");
        if (!idPattern.matcher(txtEmployeeId.getText()).matches()) {
            addError(txtEmployeeId);
            Savebtn.setDisable(true);

        }else{
            removeError(txtEmployeeId);
            Savebtn.setDisable(false);
        }
    }

    public void txtEmployeeNameKeyReleased(KeyEvent keyEvent) {
        Pattern idPattern = Pattern.compile("^[a-zA-Z ]*$");
        if (!idPattern.matcher(txtName.getText()).matches()) {
            addError(txtName);
            Savebtn.setDisable(true);

        }else{
            removeError(txtName);
            Savebtn.setDisable(false);
        }
    }


    public void txtContactNumberKeyReleased(KeyEvent keyEvent) {
        Pattern idPattern = Pattern.compile("^[0-9]{10}$");
        if (!idPattern.matcher(txtContactNumber.getText()).matches()) {
            addError(txtContactNumber);
            Savebtn.setDisable(true);

        }else{
            removeError(txtContactNumber);
            Savebtn.setDisable(false);
        }
    }

    private void removeError(TextField txtVehicleId) {
        txtVehicleId.setStyle("-fx-border-color: red;  -fx-background-radius: 5; -fx-background-radius: 5");
    }

    private void addError(TextField txtVehicleId) {
        txtVehicleId.setStyle("-fx-border-color: red;  -fx-background-radius: 5; -fx-background-radius: 5");
    }


    public void txtAddressKeyReleased(KeyEvent keyEvent) {
        Pattern idPattern = Pattern.compile("^[a-zA-Z 0-9 ]*$");
        if (!idPattern.matcher(txtAdress.getText()).matches()) {
            addError(txtAdress);
            Savebtn.setDisable(true);

        }else{
            removeError(txtAdress);
            Savebtn.setDisable(false);
        }
    }
}
