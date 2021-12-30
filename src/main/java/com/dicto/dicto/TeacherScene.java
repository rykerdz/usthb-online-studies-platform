package com.dicto.dicto;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class TeacherScene{
    @FXML
    private User user;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Label date;
    @FXML
    private JFXButton chat;

    private Authentication auth;

    public TeacherScene(User user, Authentication auth){

        this.user = user;
        this.auth = auth;
    }

    public void initialize(){
        showAnnou();
        String dt = LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        date.setText(dt);
    }


    public void showAnnou() {
        FXMloader obj = new FXMloader();
        Pane view = obj.getAnnounsTeacherPage(user, auth);
        mainPane.setCenter(view);

    }
    public void showMeetings() {
        FXMloader obj = new FXMloader();
        Pane view = obj.getMeetingsTeacherPage(user, auth);
        mainPane.setCenter(view);

    }
    public void showSharedFiles() {
        FXMloader obj = new FXMloader();
        Pane view = obj.getFilesAndRecordsTeacher(user, auth);
        mainPane.setCenter(view);

    }
    public void showHomeworks() {
        FXMloader obj = new FXMloader();
        Pane view = obj.getHomeworkPage(user, auth);
        mainPane.setCenter(view);

    }
    public void showSettings() {
        FXMloader obj = new FXMloader();
        Pane view = obj.getSettingsPage(user, auth);
        mainPane.setCenter(view);

    }
    public void showQuestions() {
        FXMloader obj = new FXMloader();
        Pane view = obj.getQuestionsPage(user, auth);
        mainPane.setCenter(view);

    }


}