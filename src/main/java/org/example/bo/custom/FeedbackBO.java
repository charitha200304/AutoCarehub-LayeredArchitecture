package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.FeedbackDTO;
import org.example.entity.Feedback;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FeedbackBO extends SuperBO {
    public ArrayList<FeedbackDTO> getAllFeedback() throws SQLException, ClassNotFoundException;
    public boolean saveFeedback(FeedbackDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateFeedback(FeedbackDTO dto) throws SQLException, ClassNotFoundException;
    public boolean deleteFeedback(String id) throws SQLException, ClassNotFoundException;
    public Feedback searchFeedback(String id) throws SQLException, ClassNotFoundException;
}
