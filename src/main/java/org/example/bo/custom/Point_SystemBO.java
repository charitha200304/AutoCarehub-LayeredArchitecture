package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.Point_SystemDTO;
import org.example.entity.Point_System;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Point_SystemBO extends SuperBO {
    public ArrayList<Point_SystemDTO> getAllPointSystem() throws SQLException, ClassNotFoundException;
    public boolean savePoint(Point_SystemDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updatePoint(Point_SystemDTO dto) throws SQLException, ClassNotFoundException;
    public boolean deletePoint(String id) throws SQLException, ClassNotFoundException;
    public Point_System searchPoint(String id) throws SQLException, ClassNotFoundException;
}
