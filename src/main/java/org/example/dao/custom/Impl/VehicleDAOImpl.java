package org.example.dao.custom.Impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.VehicleDAO;
import org.example.entity.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {
    @Override
    public ArrayList<Vehicle> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Vehicle> allVehicle = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM vehicle");
        while (rst.next()) {
            Vehicle vehicle = new Vehicle(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4));
            allVehicle.add(vehicle);
        }
        return allVehicle;
    }

    @Override
    public boolean add(Vehicle entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "INSERT INTO vehicle VALUES(?, ?, ?, ?)", entity.getV_id(), entity.getModel(), entity.getType(), entity.getCus_id());
    }

    @Override
    public boolean delete(String V_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "DELETE FROM vehicle WHERE V_id=?", V_id);
    }

    @Override
    public boolean update(Vehicle entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE vehicle SET Model=?, Type=?,Cus_id=? WHERE V_id=?", entity.getModel(), entity.getType(), entity.getCus_id(), entity.getV_id());
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
        ResultSet rst = SQLUtil.execute( "SELECT V_id FROM vehicle");
        List<String> idList = new ArrayList<>();
        while (rst.next()) {
            idList.add(rst.getString("V_id"));
        }
        return idList;
    }
    }

