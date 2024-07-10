package org.example.bo.custom.Impl;

import javafx.collections.ObservableList;
import org.example.bo.custom.Place_orderBo;
import org.example.dto.ServiceDTO;
import org.example.entity.Service;
import org.example.view.tdm.ItemTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class Place_orderBOImpl implements Place_orderBo {
    @Override
    public ArrayList<ServiceDTO> getAllService() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveService(ServiceDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateService(ServiceDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteService(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Service searchService(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String fintLastServiceId() {
        return null;
    }

    @Override
    public boolean saveService(Service service, ObservableList<ItemTm> observableList) {
        return false;
    }
}
