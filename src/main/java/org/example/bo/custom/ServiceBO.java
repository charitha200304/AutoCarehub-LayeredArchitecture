package org.example.bo.custom;

import javafx.collections.ObservableList;
import org.example.bo.SuperBO;
import org.example.dto.ServiceDTO;
import org.example.entity.Service;
import org.example.view.tdm.ItemTm;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ServiceBO extends SuperBO {
    public ArrayList<ServiceDTO> getAllService() throws SQLException, ClassNotFoundException;
    public boolean saveService(ServiceDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateService(ServiceDTO dto) throws SQLException, ClassNotFoundException;
    public boolean deleteService(String id) throws SQLException, ClassNotFoundException;
    public Service searchService(String id) throws SQLException, ClassNotFoundException;

    String fintLastServiceId();

}
