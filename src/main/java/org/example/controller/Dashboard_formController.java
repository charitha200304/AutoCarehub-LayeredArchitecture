package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Dashboard_formController {

    @FXML
    private Label UserIdLable;

    @FXML
    private Label UserNameLable;

    @FXML
    private Label fxDailyCustomerCount;

    @FXML
    private Label fxEmployeeAttendance;

    /*
    public void initialize() throws SQLException {
getEmployeeAttendance();
getDailyCustomerCount();

    }

    private void getDailyCustomerCount() {
        CustomerRepo customerRepo = new CustomerRepo();

        try{
            int count = customerRepo.countDailyCustomer();
            fxDailyCustomerCount.setText(String.valueOf("0"+count));

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

    }


    private void getEmployeeAttendance() {
        EmployeeAttendanceRepo employeeAttendanceRepo = new EmployeeAttendanceRepo();

        try{
            int count = employeeAttendanceRepo.countemployeeattendance();
            fxEmployeeAttendance.setText(String.valueOf("0"+count));

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

    }

     */
}
