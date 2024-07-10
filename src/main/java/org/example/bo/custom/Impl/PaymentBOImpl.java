package org.example.bo.custom.Impl;

import org.example.bo.custom.PaymentBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.PaymentDAO;
import org.example.dto.PaymentDTO;
import org.example.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Payment);

    @Override
    public ArrayList<PaymentDTO> getAllPayment() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDTO> allPayment= new ArrayList<>();
        ArrayList<Payment> all = paymentDAO.getAll();
        for (Payment c : all) {
            allPayment.add(new PaymentDTO(c.getPayment_id(), c.getAmount(),c.getDate(),c.getPayment_methods(),c.getCus_id()));
        }
        return allPayment;
    }

    @Override
    public boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.add(new Payment(dto.getPayment_id(), dto.getAmount(), dto.getDate(), dto.getPayment_methods(),dto.getCus_id()));
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(dto.getPayment_id(), dto.getAmount(), dto.getDate(), dto.getPayment_methods(),dto.getCus_id()));
    }

    @Override
    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(id);
    }

    @Override
    public Payment searchPayment(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.searchById(id);
    }
}
