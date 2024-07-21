package org.example.bo.custom.Impl;

import javafx.collections.ObservableList;
import org.example.bo.custom.Place_orderBo;
import org.example.dao.DAOFactory;
import org.example.dao.custom.Inventory_itemDAO;
import org.example.dao.custom.Point_SystemDAO;
import org.example.dao.custom.ServiceDAO;
import org.example.dao.custom.VehicleDAO;
import org.example.db.DbConnection;
import org.example.dto.ServiceDTO;
import org.example.entity.Inventory_item;
import org.example.entity.Point_System;
import org.example.entity.Service;
import org.example.entity.Vehicle;
import org.example.view.tdm.ItemTm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Place_orderBOImpl implements Place_orderBo {
    @Override
    public ArrayList<ServiceDTO> getAllService() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveService(ServiceDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateService(ServiceDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteService(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Service searchService(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String fintLastServiceId() {
        return null;
    }
    Point_SystemDAO point_systemDAO = (Point_SystemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Point_System);
    Inventory_itemDAO inventory_itemDAO = (Inventory_itemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Inventory_item);
    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Vehicle);
    ServiceDAO serviceDAO = (ServiceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Service);

    @Override
    public boolean saveService(Service service, ObservableList<ItemTm> observableList) throws SQLException {
        Connection connection = null;

        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean saveOrder = save(service);
            if (saveOrder) {
                boolean save = serviceDetailsSave(service.getServiceId(), observableList);
                if (save) {
                    boolean b = updateItemQty(observableList);
                    if (b) {
                        boolean pointSave = savePoint(service);
                        if (pointSave) {
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            return false;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
            }
        }
    }

    private boolean savePoint(Service service) throws SQLException, ClassNotFoundException {
        Vehicle vehicle = vehicleDAO.searchById(service.getVehicleId());
        if (vehicle != null) {
            Point_System pointDetails = point_systemDAO.searchById(vehicle.getCus_id());
            if (pointDetails != null) {
                // update point
                double cost = Double.parseDouble(service.getCost());
                double total = cost * 1 / 100;
                pointDetails.setTotal_point(String.valueOf(Integer.parseInt(pointDetails.getTotal_point()) + total));
                point_systemDAO.update(pointDetails);
            } else {
                // save point
                String lastPointId = String.valueOf(point_systemDAO.fintlastPoinId(pointDetails.getPoint_id()));
                String nextId = null;
                if (lastPointId != null) {
                    int id = Integer.parseInt(lastPointId.substring(1));
                    nextId = "P00" + (id + 1);
                } else {
                    nextId = "P00" + 1;
                }
                double cost = Double.parseDouble(service.getCost());
                double total = cost * 1 / 100;
                Point_System point_system = new Point_System(nextId, String.valueOf(total), vehicle.getCus_id(), service.getServiceId());
                point_systemDAO.add(point_system);
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean updateItemQty(ObservableList<ItemTm> observableList) throws SQLException, ClassNotFoundException {
        for (ItemTm dto : observableList) {
            ResultSet resultSet = inventory_itemDAO.searchById(dto.getItemId());
            if (resultSet.next()) {
                int x = Integer.parseInt(resultSet.getString(4));
                int subQty = x - Integer.parseInt(dto.getQty());
                boolean b = inventory_itemDAO.update(new Inventory_item(dto.getItemId(), resultSet.getString(2), resultSet.getString(3), String.valueOf(subQty)));
                if (!b) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean serviceDetailsSave(String serviceId, ObservableList<ItemTm> observableList) throws SQLException, ClassNotFoundException {
        for (ItemTm inventory_service_details : observableList) {
            boolean b = serviceDAO.saveServiceDetails(inventory_service_details, serviceId);
            if (!b) {
                return false;
            }
        }
        return true;
    }
    private boolean save(Service service) throws SQLException, ClassNotFoundException {
        Boolean result = serviceDAO.add(service);
        return result    ;
    }
}
