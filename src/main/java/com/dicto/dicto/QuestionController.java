package com.dicto.dicto;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class QuestionController {
    private User user;
    private int id;
    private Authentication auth;
    private String studentName;
    QuestionController(User user, int id, String studentName, Authentication auth){
        this.user = user;
        this.auth = auth;
        this.id = id;
        this.studentName = studentName;

    }

    @FXML
    private Label text;
    @FXML
    private Label headerData;
    @FXML
    private Label subject;
    @FXML
    private JFXTextArea textF;

    public void initialize(){
        Question qst = auth.getQuestionTeacher(id);
        text.setText(qst.getBody());
        headerData.setText("Student "+studentName+" on: "+qst.getAskedOn());
        subject.setText("Subject: "+qst.getSubject());
    }

    public void send(){
        if(textF.getText() != null){
            if(auth.answerQst(id, textF.getText())){
                PopUp.showDialog("You have answered student "+studentName+" question", "Success");
            }
            else {
                PopUp.showDialog("An error occurred please try again", "Error");
            }
        }
        else  PopUp.showDialog("Please write an answer!", "Error");
    }
}
