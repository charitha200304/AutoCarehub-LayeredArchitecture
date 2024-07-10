package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.ServiceDetailsDTO;
import org.example.entity.ServiceDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ServiceDetailsBO extends SuperBO {
    public ArrayList<ServiceDetailsDTO> getAllServiceDetails() throws SQLException, ClassNotFoundException;
    public boolean saveServiceDetails(ServiceDetailsDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateServiceDetails(ServiceDetailsDTO dto) throws SQLException, ClassNotFoundException;
    public boolean deleteServiceDetails(String id) throws SQLException, ClassNotFoundException;
    public ServiceDetails searchServiceDetails(String id) throws SQLException, ClassNotFoundException;
}
