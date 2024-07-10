package org.example.dao.custom.Impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.CustomerDAO;
import org.example.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomer = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer");
        while (rst.next()) {
            Customer customer = new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4));
            allCustomer.add(customer);
        }
        return allCustomer;
    }

    @Override
    public boolean add(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customer (Cus_id,Name,Date,Contact_number) VALUES (?,?,?,?)", entity.getCus_id(), entity.getName(), entity.getDate(), entity.getContact_number());
    }

    @Override
    public boolean delete(String Cus_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customer WHERE Cus_id=?", Cus_id);
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET name=?,Date=?, Contact_number=? WHERE Cus_id=?", entity.getName(), entity.getDate(), entity.getContact_number(), entity.getCus_id());
    }

    @Override
    public Customer searchByContactNumber(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer WHERE Contact_number=?", id + "");
        rst.next();
        return new Customer(id + "", rst.getString("Name"), rst.getString("Date"), rst.getString("Contact_number"));
    }

    @Override
    public Customer searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer WHERE Cus_id=?", id + "");
        rst.next();
        return new Customer(id + "", rst.getString("Name"), rst.getString("Date"), rst.getString("Contact_number"));
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute( "SELECT Cus_id FROM customer");
        List<String> idList = new ArrayList<>();
        while (rst.next()) {
            idList.add(rst.getString("Cus_id"));
        }
        return idList;
    }
}
