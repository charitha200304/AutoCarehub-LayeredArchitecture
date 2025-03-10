package org.example.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    public boolean add(T entity) throws SQLException,ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public boolean update(T entity) throws SQLException, ClassNotFoundException;
    public <T> T searchById(String id) throws SQLException, ClassNotFoundException;
    public <T> T searchByContactNumber(String id) throws SQLException, ClassNotFoundException;
    List<String> getIds() throws SQLException, ClassNotFoundException;


}
