package org.example.bo.custom.Impl;

import javafx.collections.ObservableList;
import org.example.bo.custom.ServiceBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.Inventory_itemDAO;
import org.example.dao.custom.Point_SystemDAO;
import org.example.dao.custom.ServiceDAO;
import org.example.dao.custom.VehicleDAO;
import org.example.db.DbConnection;
import org.example.dto.ServiceDTO;
import org.example.entity.*;
import org.example.view.tdm.ItemTm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceBOImpl implements ServiceBO {
    ServiceDAO serviceDAO = (ServiceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Service);

    @Override
    public ArrayList<ServiceDTO> getAllService() throws SQLException, ClassNotFoundException {
        ArrayList<ServiceDTO> allService = new ArrayList<>();
        ArrayList<Service> all = serviceDAO.getAll();
        for (Service c : all) {
            allService.add(new ServiceDTO(c.getServiceId(), c.getCost(), c.getDate(), c.getVehicleId()));
        }
        return allService;
    }

    @Override
    public boolean saveService(ServiceDTO dto) throws SQLException, ClassNotFoundException {
        return serviceDAO.add(new Service(dto.getServiceId(), dto.getCost(), dto.getDate(), dto.getVehicleId()));
    }

    @Override
    public boolean updateService(ServiceDTO dto) throws SQLException, ClassNotFoundException {
        return serviceDAO.update(new Service(dto.getServiceId(), dto.getCost(), dto.getDate(), dto.getVehicleId()));
    }

    @Override
    public boolean deleteService(String id) throws SQLException, ClassNotFoundException {
        return serviceDAO.delete(id);
    }

    @Override
    public Service searchService(String id) throws SQLException, ClassNotFoundException {
        return serviceDAO.searchById(id);
    }

    @Override
    public String fintLastServiceId() {
        return null;
    }
}

