package org.example.dao.custom.Impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.EmployeeDAO;
import org.example.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployee = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee");
        while (rst.next()) {
            Employee employee = new Employee(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4));
            allEmployee.add(employee);
        }
        return allEmployee;
    }

    @Override
    public boolean add(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO employee VALUES(?, ?, ?, ?)", entity.getEmployee_id(), entity.getName(), entity.getAddress(), entity.getContact_number());
    }

    @Override
    public boolean delete(String Employee_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM employee WHERE Employee_id=?", Employee_id);
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employee SET Name=?, Address=?,Contact_number=? WHERE Employee_id=?;", entity.getName(), entity.getAddress(), entity.getContact_number(), entity.getEmployee_id());
    }

    @Override
    public <T> T searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public <T> T searchByContactNumber(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute( "SELECT Employee_id FROM employee");
        List<String> idList = new ArrayList<>();
        while (rst.next()) {
            idList.add(rst.getString("Employee_id"));
        }
        return idList;
    }
    }

