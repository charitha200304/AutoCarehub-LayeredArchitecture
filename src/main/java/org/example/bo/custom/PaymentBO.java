package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.PaymentDTO;
import org.example.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    public ArrayList<PaymentDTO> getAllPayment() throws SQLException, ClassNotFoundException;
    public boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;
    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException;
    public Payment searchPayment(String id) throws SQLException, ClassNotFoundException;
}
