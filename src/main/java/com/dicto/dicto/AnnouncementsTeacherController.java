package com.dicto.dicto;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class AnnouncementsTeacherController {
    private User user;
    private Authentication auth;
    AnnouncementsTeacherController(User user, Authentication auth){
        this.user = user;
        this.auth = auth;
    }

    @FXML
    private Label latestAnnou;
    @FXML
    private Label body;

    @FXML
    private JFXTextField annousi;

    @FXML
    private Label id;


    public void initialize(){
        id.setText(user.getId());
        Body data = auth.getLatestAnnou(user.getClassroom_id(), user.getId());
        latestAnnou.setText(data.getTimestamp());
        body.setText(data.getText());

    }

    public void sendAnnouncement(){
        if (auth.sendAnnou(user.getClassroom_id(), user.getId(), annousi.getText())){
            PopUp.showDialog("You have successfully sent the announcement", "Success");
        }
        else{
            PopUp.showDialog("Something went wrong... try again", "Error");
        }
    }


}
