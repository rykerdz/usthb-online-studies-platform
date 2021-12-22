package com.dicto.dicto;

import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URI;
import java.util.*;

public class HomeworkController {
    private Authentication auth;
    private User user;
    HomeworkController(User user, Authentication auth)
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
        /**
        vbox.getChildren().add(createTitleModule(" ALGO3", 2));
        vbox.getChildren().add(createModule("17 december 2021", "1h 30min", 1,true));
        vbox.getChildren().add(createModule("19 december 2021", "1h 30min", 1,false));
         **/
        int classroom_id = auth.getClassroomId(this.user);
        Hashtable<String, ArrayList<Body>> items = auth.getHomeworks(classroom_id);
        Set<String> setOfKeys = items.keySet();

        // Creating an Iterator object to
        // iterate over the given Hashtable
        List<String> listOfkeys = new ArrayList<String>(setOfKeys);

        // to reverse LinkedHashSet contents
        Collections.reverse(listOfkeys);
        int i = 0;

        for (String key : listOfkeys) {
            int count = items.get(key).size();
            vbox.getChildren().add(createTitleModule(key, count));
            for(Body data : items.get(key)) {
                if(data.isDueToday()) vbox.getChildren().add(createModule(data.getText(), data.getTimestamp(), data.getId(), true));
                else  vbox.getChildren().add(createModule(data.getText(), data.getTimestamp(), data.getId(), false));
            }
        }

    }


    private HBox createModule(String dueDate, String duration, int idd, boolean isDue) {
        HBox hbox = new HBox();
        VBox.setMargin(hbox, new Insets(20, 0, 0, 60));
        hbox.setSpacing(110);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPrefHeight(27);
        hbox.setStyle("-fx-background-color:  #05386b; -fx-background-radius: 17px");


        // module label
        Label module_l = new Label();
        module_l.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14");
        module_l.setText("Takes about: " + duration + " To Solve!");

        // time label

        Label date = new Label();
        if (isDue) {
            date.setStyle("-fx-text-fill:  #FFA500; -fx-font-size: 14");
        } else {
            date.setStyle("-fx-text-fill:  #5CDB95; -fx-font-size: 14");
        }

        date.setText(" Due Date: " + dueDate);
        hbox.getChildren().add(date);
        hbox.getChildren().add(module_l);
        Hyperlink linked = new Hyperlink();
        linked.setStyle("-fx-text-fill: #5CDB95; -fx-font-size: 14");
        linked.setText("See detailed homework");
        linked.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                openDetailedHomework(idd);
            }
        });
        hbox.getChildren().add(linked);


        return hbox;
    }
    private Label createTitleModule(String module, int count){
        Label lbl = new Label();
        lbl.setStyle("-fx-font-size: 16; -fx-text-fill: #ffffff; -fx-background-color:  #05386b");
        VBox.setMargin(lbl, new Insets(20, 0, 0, 40));
        lbl.setText(" "+module + ":   "+Integer.toString(count)+" Homework (s) ");
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

    public void openDetailedHomework(int id){
        FXMloader lol = new FXMloader();
        lol.openDetlaiedView(id, this.user.getId(), this.auth);


    }
}
