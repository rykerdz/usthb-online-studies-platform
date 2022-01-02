package com.dicto.dicto;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


public class ModifyHomeworkController {
    private Authentication auth;
    private User user;

    @FXML
    private JFXDatePicker dateP;
    @FXML
    private JFXTimePicker timeP;
    @FXML
    private JFXTextField titleP;
    @FXML
    private JFXTextField durationP;
    @FXML
    private JFXTextArea bodyP;
    private int homework_id;

    ModifyHomeworkController(User user, int homework_id, Authentication auth)
    {
        this.homework_id = homework_id;
        this.auth = auth;
        this.user = user;
    }

    public void initialize(){
        Homework homework = auth.getHomework2Modify(homework_id);
        dateP.setValue(homework.getDate());
        timeP.setValue(homework.getTime());
        titleP.setText(homework.getTitle());
        bodyP.setText(homework.getBody());
        durationP.setText(homework.getBody());

    }

    public void send(){
        LocalDate date = dateP.getValue();
        LocalTime time = timeP.getValue();
        String title = titleP.getText();
        String duration = durationP.getText();
        String body = bodyP.getText();
            if (date != null && time != null && date.atTime(time).isAfter(LocalDateTime.now())){
                if (title != null && title.length() < 45) {
                    if (duration != null) {
                        if (body != null) {
                            // push
                            if (auth.updateHomework(homework_id, title, body, duration, date, time)) {
                                PopUp.showDialog("Successfully updated the homework", "Success");
                            }
                        } else {
                            PopUp.showDialog("Please write the homework description ", "error");
                        }
                    } else {
                        PopUp.showDialog("Please give a duration to your homework", "error");
                    }
                } else {
                    PopUp.showDialog("Please choose a short title for your homework", "error");
                }
            } else {
                PopUp.showDialog("Due day can't be empty", "error");
            }
        quit();


    }




    private void quit() {
        Stage stage = (Stage) dateP.getScene().getWindow();
        stage.hide();
        stage.close();
    }



}
