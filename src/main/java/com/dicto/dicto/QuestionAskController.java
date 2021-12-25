package com.dicto.dicto;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Hashtable;


public class QuestionAskController {
    private Authentication auth;
    private User user;
    private Hashtable<String, String> teachers;
    private int classroom_id;

    @FXML
    private JFXComboBox<String> comboBox;
    @FXML
    private JFXTextArea body;
    @FXML
    private JFXTextField subject;


    QuestionAskController(User user, Authentication auth)
    {
        this.auth = auth;
        this.user = user;
    }

    public void initialize(){
        classroom_id = auth.getClassroomId(user);
        teachers =  auth.getTeachers(classroom_id);
        ObservableList<String> teachersList = FXCollections.observableArrayList(teachers.keySet());
        comboBox.setItems(teachersList);
    }

    public void send(){
        if(body.getText().isEmpty() || subject.getText().isEmpty() || comboBox.getValue().isEmpty()){
            PopUp.showDialog("Please fill up all the informations", "Error");
        }
        else {
            String question = body.getText();
            String subjected = subject.getText();
            String teacher_id = teachers.get(comboBox.getValue());
            if(auth.sendQuestion(subjected, question, this.user.getId(), teacher_id, classroom_id))
                PopUp.showDialog("You have successfully sent the question", "Success");
            else PopUp.showDialog("Error sending the question try again later...", "Failed");
        }

    }


    private void quit() {
        Stage stage = (Stage) comboBox.getScene().getWindow();
        stage.hide();
        stage.close();
    }



}
