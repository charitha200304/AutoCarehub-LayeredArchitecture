package org.example.dao.custom.Impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.ServiceDAO;
import org.example.entity.Service;
import org.example.view.tdm.ItemTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAOImpl implements ServiceDAO {
    @Override
    public ArrayList<Service> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Service entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Service entity) throws SQLException, ClassNotFoundException {
        return false;
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

    @Override
    public boolean saveServiceDetails(ItemTm inventory_service_details, String serviceId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Inventory_Service_Details values (?,?,?,?)", inventory_service_details.getName(),inventory_service_details.getQty(),inventory_service_details.getTotal());

    }
}
