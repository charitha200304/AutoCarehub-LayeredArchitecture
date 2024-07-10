package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Point_System;

import java.sql.SQLException;

public interface Point_SystemDAO extends CrudDAO<Point_System> {
    Point_System fintlastPoinId(String point_id) throws SQLException, ClassNotFoundException;
}
