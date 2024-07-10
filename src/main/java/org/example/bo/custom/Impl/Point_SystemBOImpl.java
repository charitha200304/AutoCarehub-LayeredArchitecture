package org.example.bo.custom.Impl;

import org.example.bo.custom.Point_SystemBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.Impl.VehicleDAOImpl;
import org.example.dao.custom.Point_SystemDAO;
import org.example.dao.custom.VehicleDAO;
import org.example.dto.Point_SystemDTO;
import org.example.entity.Point_System;
import org.example.entity.Service;
import org.example.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;

public class Point_SystemBOImpl implements Point_SystemBO {
    Point_SystemDAO point_systemDAO = (Point_SystemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Point_System);
    VehicleDAO vehicleDAO = new VehicleDAOImpl();
    @Override
    public ArrayList<Point_SystemDTO> getAllPointSystem() throws SQLException, ClassNotFoundException {
        ArrayList<Point_SystemDTO> allPointSystem= new ArrayList<>();
        ArrayList<Point_System> all = point_systemDAO.getAll();
        for (Point_System c : all) {
            allPointSystem.add(new Point_SystemDTO(c.getPoint_id(), c.getTotal_point(),c.getCus_id(),c.getSh_id()));
        }
        return allPointSystem;
    }

    @Override
    public boolean savePoint(Point_SystemDTO dto) throws SQLException, ClassNotFoundException {
        return point_systemDAO.add(new Point_System(dto.getPoint_id(), dto.getTotal_point(), dto.getCus_id(), dto.getSh_id()));
    }

    @Override
    public boolean updatePoint(Point_SystemDTO dto) throws SQLException, ClassNotFoundException {
        return point_systemDAO.update(new Point_System(dto.getPoint_id(), dto.getTotal_point(), dto.getCus_id(), dto.getSh_id()));
    }

    @Override
    public boolean deletePoint(String id) throws SQLException, ClassNotFoundException {
        return point_systemDAO.delete(id);
    }

    @Override
    public Point_System searchPoint(String id) throws SQLException, ClassNotFoundException {
        return point_systemDAO.searchById(id);
    }
    /*
    private static boolean savePoint(Service service) throws SQLException {
        Vehicle vehicle = vehicleDAO.searchById(service.getVehicleId());
        if (vehicle!=null){
            Point_System pointDetails = VehicleDAO.search(vehicle.getCus_id());
            if (pointDetails!=null){
                //update point
                double cost = Double.parseDouble(service.getCost());
                double total = cost * 1 / 100;
                pointDetails.setTotal_point(String.valueOf(Integer.parseInt(pointDetails.getTotal_point())+total));
                Point_SystemRepo.update(pointDetails);
            }else {
                //save point
                String lastPointId =Point_SystemRepo.fintlastPoinId();
                String nextIs=null;
                if (lastPointId!=null){
                    int id = Integer.parseInt(lastPointId.substring(1));
                    nextIs="P00"+(id+1);
                }else {
                    nextIs="P00"+1;
                }
                double cost = Double.parseDouble(service.getCost());
                double total = cost * 1 / 100;
                Point_System point_system = new Point_System(nextIs,String.valueOf(total),vehicle.getCus_id(),service.getServiceId());
                boolean save = Point_SystemRepo.save(point_system);
            }
            return true;
        } else return false;
    }
    */
}
