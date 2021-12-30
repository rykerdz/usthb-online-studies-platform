package com.dicto.dicto;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class OnlineMeetingsTeacherController {
    private Authentication auth;
    private User user;

    OnlineMeetingsTeacherController(User user, Authentication auth) {
        this.user = user;
        this.auth = auth;

    }

    @FXML
    private VBox vbox;
    @FXML
    private Label time1;
    @FXML
    private Label time2;
    @FXML
    private Label time3;
    @FXML
    private Label day1;
    @FXML
    private Label day2;
    @FXML
    private Label day3;
    @FXML
    private Label type1;
    @FXML
    private Label type2;
    @FXML
    private Label type3;
    @FXML
    private JFXButton modify1;
    @FXML
    private JFXButton modify2;
    @FXML
    private JFXButton modify3;
    @FXML
    private JFXButton cancel1;
    @FXML
    private JFXButton cancel2;
    @FXML
    private JFXButton cancel3;
    @FXML
    private HBox hbo1;
    @FXML
    private HBox hbo2;
    @FXML
    private HBox hbo3;
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


    @FXML
    private Label id;

    public void initialize() {
        String[] items = {"TD", "TP", "COUR"};
        typeP.setItems(FXCollections.observableArrayList(items));
        id.setText(user.getId());
        ObservableList<Body> data = auth.getMeetingsTeacher(user.getId(), user.getClassroom_id());
        if (data.size()>=1) {
            Body body1 = data.get(0);
            day1.setText(body1.getText());
            time1.setText(body1.getTimestamp() + " - " + body1.getText2());
            type1.setText(body1.getText4());
            modify1.setOnAction(e -> modify(body1.getId()));
            cancel1.setOnAction(e -> cancel(body1.getId()));
        } else {
            hbo1.setVisible(false);
        }
        if (data.size()>=2) {
            Body body1 = data.get(1);
            day2.setText(body1.getText());
            time2.setText(body1.getTimestamp() + " - " + body1.getText2());
            type2.setText(body1.getText4());
            modify2.setOnAction(e -> modify(body1.getId()));
            cancel2.setOnAction(e -> cancel(body1.getId()));
        } else {
            hbo2.setVisible(false);
        }
        if (data.size()>=3) {
            Body body1 = data.get(2);
            day3.setText(body1.getText());
            time3.setText(body1.getTimestamp() + " - " + body1.getText2());
            type3.setText(body1.getText4());
            modify3.setOnAction(e -> modify(body1.getId()));
            cancel3.setOnAction(e -> cancel(body1.getId()));
        } else {
            hbo3.setVisible(false);
        }


    }

    private void modify(int id) {
        FXMloader loader = new FXMloader();
        loader.openModifyMeeting(user, id, auth);

    }

    private void cancel(int id) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Cancel The meeting ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            //do stuff
            if(auth.deleteMeeting(id)){
                PopUp.showDialog("Meeting canceled!", "Success");

            }
            else {
                PopUp.showDialog("error occured try again later","error");
            }
        }

    }

    public void addMeeting() {
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
                        if (auth.addMeeting(user.getClassroom_id(), user.getId(), starttimee, endtimee, type, link)){
                            PopUp.showDialog("Successfully added the meeting!!", "Success");
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
    }
}
