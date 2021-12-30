package com.dicto.dicto;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Hashtable;


public class ModifyMeetingController {
    private Authentication auth;
    private User user;
    private int id;

    @FXML
    private JFXDatePicker dateP;
    @FXML
    private JFXTimePicker starttimeP;
    @FXML
    private JFXTimePicker endtimeP;
    @FXML
    private JFXComboBox<String> typeP;
    @FXML
    private JFXTextField meetinglinkP;


    ModifyMeetingController(User user, int id, Authentication auth)
    {
        this.auth = auth;
        this.user = user;
        this.id = id;
    }

    public void initialize(){
        Body body = auth.getMeetingInfo(id);
        dateP.setValue(body.getDate());
        starttimeP.setValue(body.getStarttime());
        endtimeP.setValue(body.getEndtime());
        String[] items = {"TD", "TP", "COUR"};
        typeP.setItems(FXCollections.observableArrayList(items));
        typeP.setValue(body.getText());
        meetinglinkP.setText(body.getText2());

    }

    public void send(){
        LocalDate date = dateP.getValue();
        LocalTime starttime = starttimeP.getValue();
        LocalTime endtime = endtimeP.getValue();
        String type = typeP.getValue();
        String link = meetinglinkP.getText();
        if (date.isAfter(LocalDate.now())) {
            if (starttime.isBefore(endtime)) {
                if (type != null) {
                    if (link != null) {
                        // push
                        LocalDateTime starttimee = LocalDateTime.of(date, starttime);
                        LocalDateTime endtimee = LocalDateTime.of(date, endtime);
                        if (auth.updateMeeting(id, starttimee, endtimee, type, link)){
                            PopUp.showDialog("Successfully modified the meeting!!", "Success");
                        }
                    } else {
                        PopUp.showDialog("Please fill the link field", "error");
                    }
                } else {
                    PopUp.showDialog("Please choose the type of this meeting", "error");
                }
            } else {
                PopUp.showDialog("Start time must be before end time", "error");
            }
        } else {
            PopUp.showDialog("Meeting day must be after today", "error");
        }
        quit();


    }


    private void quit() {
        Stage stage = (Stage) typeP.getScene().getWindow();
        stage.hide();
        stage.close();
    }



}
