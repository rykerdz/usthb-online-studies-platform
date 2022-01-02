package com.dicto.dicto;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Hashtable;
import java.util.List;


public class AddHomeworkController {
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
    @FXML
    private Label selected;
    private int fileid=-1;

    AddHomeworkController(User user, Authentication auth)
    {
        this.auth = auth;
        this.user = user;
    }

    public void initialize(){
    }

    public void send(){
        LocalDate date = dateP.getValue();
        LocalTime time = timeP.getValue();
        String title = titleP.getText();
        String duration = durationP.getText();
        String body = bodyP.getText();
        if(body != null) {
            if (date != null && time != null && date.atTime(time).isAfter(LocalDateTime.now())) {
                if (title != null && title.length() < 45) {
                    if (duration != null) {
                        if (fileid>0) {
                            // push
                            if (auth.addHomework(user.getClassroom_id(), user.getId(), title, body, duration, date, time, fileid)) {
                                PopUp.showDialog("Successfully Added the homework", "Success");
                                quit();
                            }
                        } else {
                            PopUp.showDialog("Please upload the homework file ", "error");
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
        }
        else {
            PopUp.showDialog("Please write the homework description", "error");
        }


    }
    public void chooseFile(){
        FileChooser fc = new FileChooser();
        List<File> selectedFiles = fc.showOpenMultipleDialog(null);
        String FILE_NAME ="";
        String fileNames = "";
        if (selectedFiles != null) {
            FILE_NAME = selectedFiles.get(0).getName();
            fileNames += FILE_NAME + " ,";
            fileid = auth.uploadFileTeacher(user.getClassroom_id(), user.getId(), FILE_NAME, selectedFiles.get(0), "Homework");
        }
        selected.setText(fileNames);
    }



    private void quit() {
        Stage stage = (Stage) selected.getScene().getWindow();
        stage.hide();
        stage.close();
    }



}
