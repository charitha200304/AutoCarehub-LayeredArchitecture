package org.example.dao.custom.Impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.EmployeeAttendanceDAO;
import org.example.entity.EmployeeAttendance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeAttendanceDAOImpl implements EmployeeAttendanceDAO {
    @Override
    public ArrayList<EmployeeAttendance> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeAttendance> allEmployeeAttendance = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Attendance");
        while (rst.next()) {
            EmployeeAttendance employeeAttendance = new EmployeeAttendance(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5));
            allEmployeeAttendance.add(employeeAttendance);
        }
        return allEmployeeAttendance;
    }

    @Override
    public boolean add(EmployeeAttendance entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO attendance VALUES(?, ?, ?, ?,?)", entity.getAttendance_id(), entity.getEmployee_id(), entity.getDate(), entity.getIn_time(),entity.getOut_time());
    }

    @Override
    public boolean delete(String Attendance_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM attendance WHERE Attendane_id=?", Attendance_id);
    }

    @Override
    public boolean update(EmployeeAttendance entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE attendance SET Employee_id=?,Date=?, In_time=?,Out_time=? WHERE Attendance_id=?", entity.getEmployee_id(), entity.getDate(), entity.getIn_time(), entity.getOut_time(),entity.getAttendance_id());
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
        return null;
    }

}
