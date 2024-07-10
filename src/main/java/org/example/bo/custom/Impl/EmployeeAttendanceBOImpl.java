package org.example.bo.custom.Impl;

import org.example.bo.custom.EmployeeAttendanceBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.EmployeeAttendanceDAO;
import org.example.dto.EmployeeAttendanceDTO;
import org.example.entity.EmployeeAttendance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeAttendanceBOImpl implements EmployeeAttendanceBO {

    EmployeeAttendanceDAO employeeAttendanceDAO = (EmployeeAttendanceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EmployeeAttendance);

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<EmployeeAttendanceDTO> getAllEmployeeAttendance() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeAttendanceDTO> allEmployeeAttendance= new ArrayList<>();
        ArrayList<EmployeeAttendance> all = employeeAttendanceDAO.getAll();
        for (EmployeeAttendance c : all) {
            allEmployeeAttendance.add(new EmployeeAttendanceDTO(c.getAttendance_id(), c.getEmployee_id(),c.getDate(),c.getIn_time(),c.getOut_time()));
        }
        return allEmployeeAttendance;
    }

    @Override
    public boolean saveEmployeeAttendance(EmployeeAttendanceDTO dto) throws SQLException, ClassNotFoundException {
        return employeeAttendanceDAO.add(new EmployeeAttendance(dto.getAttendance_id(), dto.getEmployee_id(), dto.getDate(), dto.getIn_time(),dto.getOut_time()));
    }

    @Override
    public boolean updateEmployeeAttendance(EmployeeAttendanceDTO dto) throws SQLException, ClassNotFoundException {
        return employeeAttendanceDAO.update(new EmployeeAttendance(dto.getAttendance_id(), dto.getEmployee_id(), dto.getDate(), dto.getIn_time(),dto.getOut_time()));
    }

    @Override
    public boolean deleteEmployeeAttendance(String id) throws SQLException, ClassNotFoundException {
        return employeeAttendanceDAO.delete(id);
    }

    @Override
    public EmployeeAttendance searchEmployeeAttendance(String id) throws SQLException, ClassNotFoundException {
        return employeeAttendanceDAO.searchById(id);
    }
}
