package org.example.bo.custom.Impl;

import org.example.bo.custom.Inventory_itemBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.Inventory_itemDAO;
import org.example.dto.Inventory_itemDTO;
import org.example.entity.Inventory_item;

import java.sql.SQLException;
import java.util.ArrayList;

public class Inventory_itemBOImpl implements Inventory_itemBO {
    Inventory_itemDAO inventory_itemDAO = (Inventory_itemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Inventory_item);

    @Override
    public ArrayList<Inventory_itemDTO> getAllInventoryItem() throws SQLException, ClassNotFoundException {
        ArrayList<Inventory_itemDTO> allInventoryItem= new ArrayList<>();
        ArrayList<Inventory_item> all = inventory_itemDAO.getAll();
        for (Inventory_item c : all) {
            allInventoryItem.add(new Inventory_itemDTO(c.getItem_id(), c.getDescription(),c.getPrice(),c.getQty_On_Hand()));
        }
        return allInventoryItem;
    }

    @Override
    public boolean saveInventoryItem(Inventory_itemDTO dto) throws SQLException, ClassNotFoundException {
        return inventory_itemDAO.add(new Inventory_item(dto.getItem_id(), dto.getDescription(), dto.getPrice(), dto.getQty_On_Hand()));
    }

    @Override
    public boolean updateInventoryItem(Inventory_itemDTO dto) throws SQLException, ClassNotFoundException {
        return inventory_itemDAO.update(new Inventory_item(dto.getItem_id(), dto.getDescription(), dto.getPrice(), dto.getQty_On_Hand()));
    }

    @Override
    public boolean deleteInventoryItem(String id) throws SQLException, ClassNotFoundException {
        return inventory_itemDAO.delete(id);
    }

    @Override
    public Inventory_item searchInventoryItem(String id) throws SQLException, ClassNotFoundException {
        return inventory_itemDAO.searchById(id);

    }
}
