package org.example.dao.custom.Impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.Inventory_itemDAO;
import org.example.entity.Inventory_item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Inventory_itemDAOImpl implements Inventory_itemDAO {
    @Override
    public ArrayList<Inventory_item> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Inventory_item> allInventoryItem = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Inventory_item");
        while (rst.next()) {
            Inventory_item inventory_item = new Inventory_item(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4));
            allInventoryItem.add(inventory_item);
        }
        return allInventoryItem;
    }

    @Override
    public boolean add(Inventory_item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO inventory_item VALUES(?, ?, ?, ?)", entity.getItem_id(), entity.getDescription(), entity.getPrice(), entity.getQty_On_Hand());
    }

    @Override
    public boolean delete(String Item_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM inventory_item WHERE Item_id=?", Item_id);
    }

    @Override
    public boolean update(Inventory_item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE inventory_item SET Description=?, Price=?,Qty_On_Hand=? WHERE Item_id=?", entity.getDescription(), entity.getPrice(), entity.getQty_On_Hand(), entity.getItem_id());
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
