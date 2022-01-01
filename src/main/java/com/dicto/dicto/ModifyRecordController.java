package com.dicto.dicto;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.time.LocalDate;


public class ModifyRecordController {
    private Authentication auth;
    private User user;
    private int id;

    @FXML
    private JFXDatePicker dateP;
    @FXML
    private JFXTimePicker timeP;
    @FXML
    private JFXComboBox<String> typeP;
    @FXML
    private JFXTextField recordlinkP;
    @FXML
    private JFXTextField durationP;


    ModifyRecordController(User user, int id, Authentication auth)
    {
        this.auth = auth;
        this.user = user;
        this.id = id;
    }

    public void initialize(){
        String[] items = {"TD", "TP", "COUR"};
        typeP.setItems(FXCollections.observableArrayList(items));
        Body body = auth.getRecordInfo(id);
        typeP.setValue(body.getText());
        timeP.setValue(body.getStarttime());
        dateP.setValue(body.getDate());
        durationP.setText(body.getText4());
        recordlinkP.setText(body.getText2());
    }

    public void send(){
        LocalDate date = dateP.getValue();
        String type = typeP.getValue();
        String link = recordlinkP.getText();
        if ( date != null && timeP.getValue() != null) {
            if (type != null) {
                if (link != null) {
                    if(durationP.getText() != null){
                        // push
                        if (auth.updateRecord(id, date, type, link, durationP.getText(), timeP.getValue())) {
                            PopUp.showDialog("Successfully Updated the record!!", "Success");
                        }
                    }
                    else {
                        PopUp.showDialog("Please Specify the duration of the record", "error");
                    }
                } else {
                    PopUp.showDialog("Please fill the link field", "error");
                }
            } else {
                PopUp.showDialog("Please choose the type of the record", "error");
            }
        } else {
            PopUp.showDialog("Meeting day can't be empty", "error");
        }
        quit();


    }


    private void quit() {
        Stage stage = (Stage) typeP.getScene().getWindow();
        stage.hide();
        stage.close();
    }



}
