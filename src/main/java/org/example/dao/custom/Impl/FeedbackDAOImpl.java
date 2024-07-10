package org.example.dao.custom.Impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.FeedbackDAO;
import org.example.entity.Feedback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAOImpl implements FeedbackDAO {
    @Override
    public ArrayList<Feedback> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Feedback> allFeedback = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM feedback");
        while (rst.next()) {
            Feedback feedback = new Feedback(rst.getString(1), rst.getString(2), rst.getString(3));
            allFeedback.add(feedback);
        }
        return allFeedback;
    }

    @Override
    public boolean add(Feedback entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "INSERT INTO feedback VALUES(?, ?, ?)", entity.getF_id(), entity.getDescription(), entity.getCus_id());
    }

    @Override
    public boolean delete(String Feedback_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "DELETE FROM feedback WHERE F_id=?", Feedback_id);
    }

    @Override
    public boolean update(Feedback entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE feedback SET Description=?,Cus_id=? WHERE F_id=?", entity.getDescription(), entity.getCus_id(), entity.getF_id());
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
