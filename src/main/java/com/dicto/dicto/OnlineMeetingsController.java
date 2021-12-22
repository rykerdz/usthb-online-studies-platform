package com.dicto.dicto;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.event.ActionEvent;
import java.net.URI;
import java.util.*;

public class OnlineMeetingsController {
    private Authentication auth;
    private User user;
    OnlineMeetingsController(User user, Authentication auth)
    {
        this.user = user;
        this.auth = auth;

    }
    @FXML
    private VBox vbox;
    @FXML
    private Label id;

    public void initialize(){
        id.setText(user.getId());
        int classroom_id = auth.getClassroomId(this.user);
        Hashtable<String, ArrayList<Body>> items = auth.getCourses(classroom_id);
        Set<String> setOfKeys = items.keySet();

        // Creating an Iterator object to
        // iterate over the given Hashtable
        List<String> listOfkeys = new ArrayList<String>(setOfKeys);

        // to reverse LinkedHashSet contents
        Collections.reverse(listOfkeys);
        int i = 0;
        for (String key : listOfkeys) {
            vbox.getChildren().add(createTitleDate(key));
            for(Body data : items.get(key)){
                if (i==0){
                    vbox.getChildren().add(createDate(data.getText(), data.getTimestamp(), data.getText2(), true));
                    i++;
                }
                else vbox.getChildren().add(createDate(data.getText(), data.getTimestamp(), data.getText2(), false));
            }
        }
    }


    private HBox createDate(String time, String module, String link, boolean isFirst){
        HBox hbox = new HBox();
        VBox.setMargin(hbox, new Insets(20,0,0,60));
        hbox.setSpacing(200);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPrefHeight(37);
        hbox.setStyle("-fx-background-color:  #05386b; -fx-background-radius: 17px");


        // time label
        Label date = new Label();
        date.setStyle("-fx-text-fill:  #ffffff; -fx-border-color: white; -fx-font-size: 14");
        date.setText(time);
        // module label
        Label module_l = new Label();
        module_l.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14");
        module_l.setText(module);
        // go to meeting button
        hbox.getChildren().add(date);
        hbox.getChildren().add(module_l);
        if (isFirst) {
            JFXButton btn = new JFXButton();
            btn.setStyle("-fx-background-color:  #5CDB95; -fx-text-fill:  #05386b");
            btn.setText("GO TO MEETING");
            btn.setOnAction(e -> goToLink(link));
            hbox.getChildren().add(btn);
        }
        else{
            Label noLink = new Label();
            noLink.setStyle("-fx-text-fill:  #ffffff; -fx-font-size: 12");
            noLink.setText(" Link will be available shortly! ");
            hbox.getChildren().add(noLink);
        }



        return hbox;
    }
    private Label createTitleDate(String date){
        Label lbl = new Label();
        lbl.setStyle("-fx-font-size: 16; -fx-text-fill: #ffffff; -fx-background-color:  #05386b");
        VBox.setMargin(lbl, new Insets(20, 0, 0, 40));
        lbl.setText(date);
        return lbl;
    }
    public void goToLink(String link){
        try {
            URI uri= new URI(link);

            java.awt.Desktop.getDesktop().browse(uri);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
