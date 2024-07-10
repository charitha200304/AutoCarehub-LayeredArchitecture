package org.example.dao.custom.Impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.Service_HistoryDAO;
import org.example.entity.Service_History;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Service_HistoryDAOImpl implements Service_HistoryDAO {
    @Override
    public ArrayList<Service_History> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Service_History> allServiceHistory = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM service_history");
        while (rst.next()) {
            Service_History service_history = new Service_History(rst.getString(1), rst.getString(2), rst.getString(3));
            allServiceHistory.add(service_history);
        }
        return allServiceHistory;
    }

    @Override
    public boolean add(Service_History entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "INSERT INTO service_history VALUES(?, ?, ?)", entity.getSH_id(), entity.getDescription(), entity.getV_id());
    }

    @Override
    public boolean delete(String SH_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM service_history WHERE SH_id=?", SH_id);
    }

    @Override
    public boolean update(Service_History entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE service_history SET Description=?,V_id=? WHERE SH_id=?", entity.getDescription(), entity.getV_id(), entity.getSH_id());
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
        ResultSet rst = SQLUtil.execute( "SELECT SH_id FROM service_history");
        List<String> idList = new ArrayList<>();
        while (rst.next()) {
            idList.add(rst.getString("SH_id"));
        }
        return idList;
    }
}
