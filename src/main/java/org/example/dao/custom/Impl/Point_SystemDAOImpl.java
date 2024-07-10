package org.example.dao.custom.Impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.Point_SystemDAO;
import org.example.entity.Point_System;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Point_SystemDAOImpl implements Point_SystemDAO {
    @Override
    public ArrayList<Point_System> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Point_System> allPointSystem = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM point_system");
        while (rst.next()) {
            Point_System point_system = new Point_System(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4));
            allPointSystem.add(point_system);
        }
        return allPointSystem;
    }

    @Override
    public boolean add(Point_System entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "INSERT INTO point_system VALUES(?, ?, ?, ?)", entity.getPoint_id(), entity.getTotal_point(), entity.getCus_id(), entity.getSh_id());
    }

    @Override
    public boolean delete(String Point_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM point_system WHERE Point_id=?", Point_id);
    }

    @Override
    public boolean update(Point_System entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE point_system SET Total_point=?, Cus_id=?,SH_id = ? WHERE Point_id =?", entity.getTotal_point(), entity.getCus_id(), entity.getSh_id(), entity.getPoint_id());
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
    public Point_System searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM point_system WHERE Cus_id=?", id + "");
        rst.next();
        return new Point_System(id + "", rst.getString("Total_point"), rst.getString("Cus_id"), rst.getString("Sh_id"));
    }
}
