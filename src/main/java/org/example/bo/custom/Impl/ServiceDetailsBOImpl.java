package org.example.bo.custom.Impl;

import org.example.bo.custom.ServiceDetailsBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.ServiceDetailsDAO;
import org.example.dto.ServiceDetailsDTO;
import org.example.entity.ServiceDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceDetailsBOImpl implements ServiceDetailsBO {
    ServiceDetailsDAO serviceDetailsDAO = (ServiceDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ServiceDetails);

    @Override
    public ArrayList<ServiceDetailsDTO> getAllServiceDetails() throws SQLException, ClassNotFoundException {
        ArrayList<ServiceDetailsDTO> allServiceDetails= new ArrayList<>();
        ArrayList<ServiceDetails> all = serviceDetailsDAO.getAll();
        for (ServiceDetails c : all) {
            allServiceDetails.add(new ServiceDetailsDTO(c.getServiceId(), c.getItemId(),c.getPrice(),c.getQty()));
        }
        return allServiceDetails;
    }

    @Override
    public boolean saveServiceDetails(ServiceDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return serviceDetailsDAO.add(new ServiceDetails(dto.getServiceId(), dto.getItemId(), dto.getPrice(), dto.getQty()));
    }

    @Override
    public boolean updateServiceDetails(ServiceDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return serviceDetailsDAO.update(new ServiceDetails(dto.getServiceId(), dto.getItemId(), dto.getPrice(), dto.getQty()));
    }

    @Override
    public boolean deleteServiceDetails(String id) throws SQLException, ClassNotFoundException {
        return serviceDetailsDAO.delete(id);
    }

    @Override
    public ServiceDetails searchServiceDetails(String id) throws SQLException, ClassNotFoundException {
        return serviceDetailsDAO.searchById(id);
    }
}
