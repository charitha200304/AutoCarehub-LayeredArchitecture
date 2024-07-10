package org.example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.example.bo.BOFactory;
import org.example.bo.custom.CustomerBO;
import org.example.bo.custom.FeedbackBO;
import org.example.dto.FeedbackDTO;
import org.example.entity.Feedback;
import org.example.view.tdm.FeedbackTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Feedback_formController {

    @FXML
    private JFXButton Deletebtn;

    @FXML
    private JFXButton Savebtn;

    @FXML
    private JFXButton Updatebtn;

    @FXML
    private JFXButton clearbtn;

    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableView<FeedbackTm> tblFeedback;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtFeedbackId;
    CustomerBO customerBO  = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Customer);
    FeedbackBO feedbackBO  = (FeedbackBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Feedback);
    private List<FeedbackDTO> feedbackList = new ArrayList<>();
    public void initialize() {
        this.feedbackList = getAllFeedback();
        setCellDataFactory();
        loadAllFeedback();
        getCustomerId();
    }
    private void getCustomerId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> IdList = customerBO.getIds();

            for (String id : IdList) {
                obList.add(id);
            }
            cmbCustomerId.setItems(obList);


        } catch (SQLException | ClassNotFoundException e)  {
            throw new RuntimeException(e);
        }
    }
    private void loadAllFeedback() {
        ObservableList<FeedbackTm> tmList = FXCollections.observableArrayList();

        for (FeedbackDTO feedbackDTO : feedbackList) {
            FeedbackTm feedbackTm = new FeedbackTm(

                    feedbackDTO.getF_id(),
                    feedbackDTO.getDescription(),
                    feedbackDTO.getCus_id()

            );
            tmList.add(feedbackTm);

        }
        tblFeedback.setItems(tmList);
        FeedbackTm selectedItem = tblFeedback.getSelectionModel().getSelectedItem();
    }
    private void setCellDataFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("F_id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("Cus_id"));

    }
    private List<FeedbackDTO> getAllFeedback() {
        List<FeedbackDTO> feedbackList = null;
        try {
            feedbackList = feedbackBO.getAllFeedback();
        } catch (SQLException | ClassNotFoundException e)  {
            throw new RuntimeException(e);
        }
        return feedbackList;
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }
    private void clearFields() {
        txtFeedbackId.setText("");
        txtDescription.setText("");
        cmbCustomerId.setValue("");

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String f_id = txtFeedbackId.getText();

        try {
            boolean isDeleted = feedbackBO.deleteFeedback(f_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e)  {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String f_id = txtFeedbackId.getText();
        String description = txtFeedbackId.getText();
        String cus_id = cmbCustomerId.getValue().toString();


        Feedback feedback = new Feedback(f_id, description, cus_id);

        try {
            boolean isSaved = feedbackBO.saveFeedback(new FeedbackDTO(f_id, description, cus_id));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Feedback saved!").show();
            }
        }  catch (SQLException | ClassNotFoundException e)  {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String f_id = txtFeedbackId.getText();
        String description = txtDescription.getText();
        String cus_id = cmbCustomerId.getValue().toString();


        Feedback feedback = new Feedback(f_id, description, cus_id);

        try {
            boolean isUpdated = feedbackBO.updateFeedback(new FeedbackDTO(f_id, description, cus_id));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Feedback updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e)  {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void txtFeedbackIdKeyReleased(KeyEvent keyEvent) {
        Pattern idPattern = Pattern.compile("(F)[0-9].{1,9}$");
        if (!idPattern.matcher(txtFeedbackId.getText()).matches()) {
            addError(txtFeedbackId);
            Savebtn.setDisable(true);

        }else{
            removeError(txtFeedbackId);
            Savebtn.setDisable(false);
        }
    }

    public void txtDescriptionKeyReleased(KeyEvent keyEvent) {
        Pattern idPattern = Pattern.compile("^[a-zA-Z 0-9]*$");
        if (!idPattern.matcher(txtDescription.getText()).matches()) {
            addError(txtDescription);
            Savebtn.setDisable(true);

        }else{
            removeError(txtDescription);
            Savebtn.setDisable(false);
        }
    }
    private void removeError(TextField txtVehicleId) {
        txtVehicleId.setStyle("-fx-border-color: red;  -fx-background-radius: 5; -fx-background-radius: 5");
    }

    private void addError(TextField txtVehicleId) {
        txtVehicleId.setStyle("-fx-border-color: red;  -fx-background-radius: 5; -fx-background-radius: 5");
    }
}
