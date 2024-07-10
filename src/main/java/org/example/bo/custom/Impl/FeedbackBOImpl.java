package org.example.bo.custom.Impl;

import org.example.bo.custom.FeedbackBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.FeedbackDAO;
import org.example.dto.FeedbackDTO;
import org.example.entity.Feedback;

import java.sql.SQLException;
import java.util.ArrayList;

public class FeedbackBOImpl implements FeedbackBO {
    FeedbackDAO feedbackDAO = (FeedbackDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Feedback);

    @Override
    public ArrayList<FeedbackDTO> getAllFeedback() throws SQLException, ClassNotFoundException {
        ArrayList<FeedbackDTO> allFeedback= new ArrayList<>();
        ArrayList<Feedback> all = feedbackDAO.getAll();
        for (Feedback c : all) {
            allFeedback.add(new FeedbackDTO(c.getF_id(), c.getDescription(),c.getCus_id()));
        }
        return allFeedback;
    }

    @Override
    public boolean saveFeedback(FeedbackDTO dto) throws SQLException, ClassNotFoundException {
        return feedbackDAO.add(new Feedback(dto.getF_id(), dto.getDescription(), dto.getCus_id()));
    }

    @Override
    public boolean updateFeedback(FeedbackDTO dto) throws SQLException, ClassNotFoundException {
        return feedbackDAO.update(new Feedback(dto.getF_id(), dto.getDescription(), dto.getCus_id()));
    }

    @Override
    public boolean deleteFeedback(String id) throws SQLException, ClassNotFoundException {
        return feedbackDAO.delete(id);
    }

    @Override
    public Feedback searchFeedback(String id) throws SQLException, ClassNotFoundException {
        return feedbackDAO.searchById(id);
    }
}
