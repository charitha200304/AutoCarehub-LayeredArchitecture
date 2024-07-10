package org.example.bo.custom.Impl;

import org.example.bo.custom.Point_SystemBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.Point_SystemDAO;
import org.example.dto.Point_SystemDTO;
import org.example.entity.Point_System;

import java.sql.SQLException;
import java.util.ArrayList;

public class Point_SystemBOImpl implements Point_SystemBO {
    Point_SystemDAO point_systemDAO = (Point_SystemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Point_System);

    @Override
    public ArrayList<Point_SystemDTO> getAllPointSystem() throws SQLException, ClassNotFoundException {
        ArrayList<Point_SystemDTO> allPointSystem= new ArrayList<>();
        ArrayList<Point_System> all = point_systemDAO.getAll();
        for (Point_System c : all) {
            allPointSystem.add(new Point_SystemDTO(c.getPoint_id(), c.getTotal_point(),c.getCus_id(),c.getSh_id()));
        }
        return allPointSystem;
    }

    @Override
    public boolean savePoint(Point_SystemDTO dto) throws SQLException, ClassNotFoundException {
        return point_systemDAO.add(new Point_System(dto.getPoint_id(), dto.getTotal_point(), dto.getCus_id(), dto.getSh_id()));
    }

    @Override
    public boolean updatePoint(Point_SystemDTO dto) throws SQLException, ClassNotFoundException {
        return point_systemDAO.update(new Point_System(dto.getPoint_id(), dto.getTotal_point(), dto.getCus_id(), dto.getSh_id()));
    }

    @Override
    public boolean deletePoint(String id) throws SQLException, ClassNotFoundException {
        return point_systemDAO.delete(id);
    }

    @Override
    public Point_System searchPoint(String id) throws SQLException, ClassNotFoundException {
        return point_systemDAO.searchById(id);
    }
}
