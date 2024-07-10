package org.example.dao.custom.Impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.PaymentDAO;
import org.example.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> allPayment = new ArrayList<>();
        ResultSet rst = SQLUtil.execute( "SELECT * FROM payment");
        while (rst.next()) {
            Payment payment = new Payment(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4),rst.getString(5));
            allPayment.add(payment);
        }
        return allPayment;
    }

    @Override
    public boolean add(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO payment VALUES(?, ?, ?, ?,?)", entity.getPayment_id(), entity.getAmount(), entity.getDate(), entity.getPayment_methods(), entity.getCus_id());
    }

    @Override
    public boolean delete(String Payment_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM payment WHERE Payment_id=?", Payment_id);
    }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE payment SET  Amount=?,Date=?, Payment_methods=?,Cus_id =? WHERE Payment_id=?", entity.getAmount(), entity.getDate(), entity.getPayment_methods(), entity.getCus_id(),entity.getPayment_id());
    }

    @Override
    public <T> T searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public <T> T searchByContactNumber(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}
