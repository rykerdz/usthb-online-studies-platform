package com.dicto.dicto;

import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;


public class QuestionDetailsController {
    private Authentication auth;
    private int questionId;
    private String userId, teacherName;
    @FXML
    private VBox vbox;

    QuestionDetailsController(int questionId, String userId, String teacherName, Authentication auth)
    {
        this.questionId = questionId;
        this.userId = userId;
        this.auth = auth;
        this.teacherName = teacherName;
    }

    public void initialize(){
        Question qst = auth.getQuestionDetails(this.questionId);
        vbox.getChildren().add(createTitle(" Question details "));
        vbox.getChildren().add(createDetails(qst.getAskedOn(), qst.getRespondedOn(), qst.getSubject(), qst.getBody(), this.teacherName, qst.getResponse()));
    }

    public Label createTitle(String text){
        Label label = new Label();
        label.setAlignment(Pos.CENTER);
        label.setText(text);
        label.setStyle("-fx-background-color:  #05386b; -fx-font-size: 24; -fx-text-fill: #ffffff");
        return label;

    }
    public VBox createDetails(String questionDate, String responseDate, String subject, String body, String teacherName, String response){
        VBox vbox = new VBox();
        VBox.setMargin(vbox, new Insets(20,40,0,40));
        HBox hbox = new HBox();
        Label label1 = new Label();
        label1.setText("You Asked: "+teacherName);
        label1.setStyle("-fx-font-size: 13; -fx-text-fill: #000000");
        Label label2 = new Label();
        label2.setText("on: "+questionDate);
        label2.setStyle("-fx-font-size: 13; -fx-text-fill: #000000");
        Label label3 = new Label();
        label3.setText("Responded on:  "+responseDate);
        label3.setStyle("-fx-font-size: 13; -fx-text-fill: #000000");
        hbox.getChildren().add(label1);
        hbox.getChildren().add(label2);
        hbox.getChildren().add(label3);
        hbox.setSpacing(25);
        vbox.getChildren().add(hbox);
        // subject
        //HBox hbxing = new HBox();

        //Label labe1 = new Label();
        //label1.setText("Subject: "+subject);
        //label1.setStyle("-fx-font-size: 14; -fx-text-fill: #000000");
        //hbxing.getChildren().add(labe1);
        //vbox.getChildren().add(hbxing);
        //


        HBox hboxed = new HBox();
        Label lbl = new Label();
        lbl.setStyle("-fx-font-size: 15; -fx-text-fill: #05386b; -fx-font-weight: BOLD");
        lbl.setText("SUBJECT: "+subject);
        VBox.setMargin(hboxed, new Insets(60,0,30,0));
        hboxed.getChildren().add(lbl);
        vbox.getChildren().add(hboxed);
        VBox vbox2 = new VBox();
        Label label4 = new Label();
        label4.setText("YOUR QUESTION: ");
        label4.setStyle("-fx-font-size: 15; -fx-text-fill: #05386b; -fx-font-weight: BOLD");
        Label label5 = new Label();
        label5.setText(body);
        label5.setStyle("-fx-font-size: 14; -fx-text-fill: #000000");
        label5.setWrapText(true);
        label5.setPadding(new Insets(0,0,0,25));
        vbox2.setSpacing(10);
        vbox2.getChildren().add(label4);
        vbox2.getChildren().add(label5);
        vbox.getChildren().add(vbox2);
        VBox.setMargin(vbox2, new Insets(40,0,0,0));
        VBox vbox3 = new VBox();
        vbox3.setSpacing(10);
        Label label6 = new Label();
        label6.setText("Teacher's answer: ");
        label6.setStyle("-fx-font-size: 15; -fx-text-fill: #05386b; -fx-font-weight: BOLD");
        HBox hbox2 = new HBox();
        hbox2.setSpacing(90);
        Label label7 = new Label();
        label7.setText(response);
        label7.setStyle("-fx-font-size: 13; -fx-text-fill: #000000");
        label7.setWrapText(true);
        label7.setPadding(new Insets(0,0,0,25));
        VBox.setMargin(label6, new Insets(40,0,0,0));
        hbox2.getChildren().add(label7);
        VBox.setMargin(hbox2, new Insets(10,0,0,0));
        vbox.getChildren().add(label6);
        vbox.getChildren().add(hbox2);
        //
        JFXButton bttn = new JFXButton();
        HBox hboxi = new HBox();
        hboxi.setAlignment(Pos.BOTTOM_RIGHT);

        bttn.setStyle("-fx-background-color: #05386b; -fx-text-fill: #5CDB95; -fx-font-size: 17");
        bttn.setText("Done");
        bttn.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                quit();
            }
        });
        VBox.setMargin(hboxi, new Insets(130, 0,0,0));


        hboxi.getChildren().add(bttn);

        vbox.getChildren().add(hboxi);



        return vbox;
    }
    private void quit() {
        Stage stage = (Stage) vbox.getScene().getWindow();
        stage.hide();
        stage.close();
    }



}
