package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.VehicleDTO;
import org.example.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VehicleBO extends SuperBO {
    List<String> getIds() throws SQLException, ClassNotFoundException;
    public ArrayList<VehicleDTO> getAllVehicle() throws SQLException, ClassNotFoundException;
    public boolean saveVehicle(VehicleDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateVehicle(VehicleDTO dto) throws SQLException, ClassNotFoundException;
    public boolean deleteVehicle(String id) throws SQLException, ClassNotFoundException;
    public Vehicle searchVehicle(String id) throws SQLException, ClassNotFoundException;
}
