package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.EmployeeAttendanceDTO;
import org.example.entity.EmployeeAttendance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeAttendanceBO extends SuperBO {
    List<String> getIds() throws SQLException, ClassNotFoundException;
    public ArrayList<EmployeeAttendanceDTO> getAllEmployeeAttendance() throws SQLException, ClassNotFoundException;
    public boolean saveEmployeeAttendance(EmployeeAttendanceDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateEmployeeAttendance(EmployeeAttendanceDTO dto) throws SQLException, ClassNotFoundException;
    public boolean deleteEmployeeAttendance(String id) throws SQLException, ClassNotFoundException;
    public EmployeeAttendance searchEmployeeAttendance(String id) throws SQLException, ClassNotFoundException;
}
