package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.Service_HistoryDTO;
import org.example.entity.Service_History;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Service_HistoryBO extends SuperBO {
    List<String> getIds() throws SQLException, ClassNotFoundException;
    public ArrayList<Service_HistoryDTO> getAllServiceHistory() throws SQLException, ClassNotFoundException;
    public boolean saveServiceHistory(Service_HistoryDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateServiceHistory(Service_HistoryDTO dto) throws SQLException, ClassNotFoundException;
    public boolean deleteServiceHistory(String id) throws SQLException, ClassNotFoundException;
    public Service_History searchServiceHistory(String id) throws SQLException, ClassNotFoundException;
}
