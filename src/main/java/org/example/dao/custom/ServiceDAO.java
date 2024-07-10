package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Service;
import org.example.view.tdm.ItemTm;

import java.sql.SQLException;

public interface ServiceDAO extends CrudDAO<Service> {
    boolean saveServiceDetails(ItemTm inventory_service_details, String serviceId) throws SQLException, ClassNotFoundException;

}
