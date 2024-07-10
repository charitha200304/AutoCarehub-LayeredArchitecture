package org.example.bo.custom.Impl;

import org.example.bo.custom.VehicleBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.VehicleDAO;
import org.example.dto.VehicleDTO;
import org.example.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleBOImpl implements VehicleBO {

    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Vehicle);

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return vehicleDAO.getIds();
    }

    @Override
    public ArrayList<VehicleDTO> getAllVehicle() throws SQLException, ClassNotFoundException {
        ArrayList<VehicleDTO> allVehicle= new ArrayList<>();
        ArrayList<Vehicle> all = vehicleDAO.getAll();
        for (Vehicle c : all) {
            allVehicle.add(new VehicleDTO(c.getV_id(), c.getModel(),c.getType(),c.getCus_id()));
        }
        return allVehicle;
    }

    @Override
    public boolean saveVehicle(VehicleDTO dto) throws SQLException, ClassNotFoundException {
        return vehicleDAO.add(new Vehicle(dto.getV_id(), dto.getModel(), dto.getType(), dto.getCus_id()));
    }

    @Override
    public boolean updateVehicle(VehicleDTO dto) throws SQLException, ClassNotFoundException {
        return vehicleDAO.update(new Vehicle(dto.getV_id(), dto.getModel(), dto.getType(), dto.getCus_id()));
    }

    @Override
    public boolean deleteVehicle(String id) throws SQLException, ClassNotFoundException {
        return vehicleDAO.delete(id);
    }

    @Override
    public Vehicle searchVehicle(String id) throws SQLException, ClassNotFoundException {
        return vehicleDAO.searchById(id);
    }
}
