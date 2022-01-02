package com.dicto.dicto;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Hashtable;


public class QuestionScene {
    private User user;
    @FXML
    private BorderPane mainPane;
    @FXML
    private JFXListView<String> listView;
    @FXML
    private Label id;
    private Authentication auth;
    private Hashtable<String, Integer> questions;

    public QuestionScene(User user, Authentication auth){

        this.user = user;
        this.auth = auth;
    }

    public void initialize(){
        id.setText(user.getId());
        questions = auth.getQuestions(user.getId(), user.getClassroom_id());
        if(questions == null || questions.keySet() == null){
            PopUp.showDialog("You have no questions from your students at the time", "Notice");
        }
        else {
            for (String key : questions.keySet()) {
                listView.getItems().add(key);
            }
            listView.setOnMouseClicked(e -> showAnnou(listView.getSelectionModel().getSelectedItem()));
        }
    }


    public void showAnnou(String key) {
        int id = questions.get(key);
        FXMloader obj = new FXMloader();
        Pane view = obj.getQuestionTeacher(user,id,key, auth);
        mainPane.setCenter(view);

    }



}