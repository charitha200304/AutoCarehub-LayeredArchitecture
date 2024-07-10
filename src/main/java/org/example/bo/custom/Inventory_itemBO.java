package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.Inventory_itemDTO;
import org.example.entity.Inventory_item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Inventory_itemBO extends SuperBO {
    public ArrayList<Inventory_itemDTO> getAllInventoryItem() throws SQLException, ClassNotFoundException;
    public boolean saveInventoryItem(Inventory_itemDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateInventoryItem(Inventory_itemDTO dto) throws SQLException, ClassNotFoundException;
    public boolean deleteInventoryItem(String id) throws SQLException, ClassNotFoundException;
    public Inventory_item searchInventoryItem(String id) throws SQLException, ClassNotFoundException;
}
