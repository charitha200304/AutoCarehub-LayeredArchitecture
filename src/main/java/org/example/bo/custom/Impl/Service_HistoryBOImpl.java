package org.example.bo.custom.Impl;

import org.example.bo.custom.Service_HistoryBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.Service_HistoryDAO;
import org.example.dto.Service_HistoryDTO;
import org.example.entity.Service_History;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Service_HistoryBOImpl implements Service_HistoryBO {
    Service_HistoryDAO service_historyDAO = (Service_HistoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Service_History);

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return service_historyDAO.getIds();
    }

    @Override
    public ArrayList<Service_HistoryDTO> getAllServiceHistory() throws SQLException, ClassNotFoundException {
        ArrayList<Service_HistoryDTO> allServiceHistory= new ArrayList<>();
        ArrayList<Service_History> all = service_historyDAO.getAll();
        for (Service_History c : all) {
            allServiceHistory.add(new Service_HistoryDTO(c.getSH_id(), c.getDescription(),c.getV_id()));
        }
        return allServiceHistory;
    }

    @Override
    public boolean saveServiceHistory(Service_HistoryDTO dto) throws SQLException, ClassNotFoundException {
        return service_historyDAO.add(new Service_History(dto.getSH_id(), dto.getDescription(), dto.getV_id()));
    }

    @Override
    public boolean updateServiceHistory(Service_HistoryDTO dto) throws SQLException, ClassNotFoundException {
        return service_historyDAO.update(new Service_History(dto.getSH_id(), dto.getDescription(), dto.getV_id()));
    }

    @Override
    public boolean deleteServiceHistory(String id) throws SQLException, ClassNotFoundException {
        return service_historyDAO.delete(id);
    }

    @Override
    public Service_History searchServiceHistory(String id) throws SQLException, ClassNotFoundException {
        return service_historyDAO.searchById(id);
    }
}
