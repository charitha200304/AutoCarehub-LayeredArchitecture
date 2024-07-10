package org.example.dao.custom.Impl;

import org.example.dao.custom.ServiceDetailsDAO;
import org.example.entity.ServiceDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDetailsDAOImpl implements ServiceDetailsDAO {
    @Override
    public ArrayList<ServiceDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(ServiceDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(ServiceDetails entity) throws SQLException, ClassNotFoundException {
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
}
