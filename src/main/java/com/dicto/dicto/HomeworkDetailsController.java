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

import java.io.File;
import java.io.FileInputStream;
import java.util.List;


public class HomeworkDetailsController {
    private Authentication auth;
    private int homeworkId;
    private String userId;
    @FXML
    private VBox vbox;
    boolean isSubmitAvailable;

    HomeworkDetailsController(int homeworkId,String userId, Authentication auth)
    {
        this.homeworkId = homeworkId;
        this.userId = userId;
        this.auth = auth;
        isSubmitAvailable = auth.isHomeworkDone(this.homeworkId, this.userId);
    }

    public void initialize(){
        Homework homework = auth.getHomeworkDetails(this.homeworkId);
        vbox.getChildren().add(createHomeworkDetails(" "+homework.getTitle()+" "));
        vbox.getChildren().add(createDetails(homework.getDueDate(), homework.getPublishDate(), homework.getBody(), homework.getTeacherName(), homework.getDuration(),"ID: "+
                homework.getFile().getId()+"" +
                "                      Type: "+homework.getFile().getType()+
                "                      File name: "+homework.getFile().getFilename()));
    }

    public Label createHomeworkDetails(String text){
        Label label = new Label();
        label.setAlignment(Pos.CENTER);
        label.setText(text);
        label.setStyle("-fx-background-color:  #05386b; -fx-font-size: 24; -fx-text-fill: #ffffff");
        return label;

    }
    public VBox createDetails(String dueDate, String publishDate, String body, String teacherName, String duration, String fileInfos){
        VBox vbox = new VBox();
        VBox.setMargin(vbox, new Insets(20,40,0,40));
        HBox hbox = new HBox();
        Label label1 = new Label();
        label1.setText("Published on: "+publishDate);
        label1.setStyle("-fx-font-size: 13; -fx-text-fill: #000000");
        Label label2 = new Label();
        label2.setText("By: "+teacherName);
        label2.setStyle("-fx-font-size: 13; -fx-text-fill: #000000");
        HBox hboxx = new HBox();
        Label label3 = new Label();
        label3.setText("Due on: "+dueDate);
        label3.setStyle("-fx-font-size: 13; -fx-text-fill: #000000");
        Label labeled = new Label();
        labeled.setText("Takes about "+duration+" To solve!");
        labeled.setStyle("-fx-font-size: 13; -fx-text-fill: #000000");
        hbox.getChildren().add(label1);
        hbox.getChildren().add(label2);
        hboxx.getChildren().add(label3);
        hboxx.getChildren().add(labeled);
        hbox.setSpacing(25);
        hboxx.setSpacing(25);
        VBox.setMargin(hboxx, new Insets(20, 0, 0,0));
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(hboxx);
        VBox vbox2 = new VBox();
        Label label4 = new Label();
        label4.setText("YOU ARE REQUESTED TO DO: ");
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
        label6.setText("ATTACHEMENTS: ");
        label6.setStyle("-fx-font-size: 15; -fx-text-fill: #05386b; -fx-font-weight: BOLD");
        HBox hbox2 = new HBox();
        hbox2.setSpacing(90);
        Label label7 = new Label();
        label7.setText(fileInfos);
        label7.setStyle("-fx-font-size: 13; -fx-text-fill: #000000");
        label7.setWrapText(true);
        label7.setPadding(new Insets(0,0,0,25));
        JFXButton btn = new JFXButton();
        btn.setStyle("-fx-background-color: #5CDB95; -fx-text-fill: #05386b");
        btn.setText("Download");
        VBox.setMargin(label6, new Insets(40,0,0,0));
        hbox2.getChildren().add(label7);
        hbox2.getChildren().add(btn);
        VBox.setMargin(hbox2, new Insets(10,0,0,0));
        vbox.getChildren().add(label6);
        vbox.getChildren().add(hbox2);
        //
        Label label8 = new Label();
        label8.setText("SUBMIT YOUR ANSWER: ");
        label8.setStyle("-fx-font-size: 15; -fx-text-fill: #05386b; -fx-font-weight: BOLD");
        HBox hbox3 = new HBox();
        hbox3.setSpacing(90);
        Label label9 = new Label();
        label9.setText("Select a file to upload: ");
        label9.setStyle("-fx-font-size: 13; -fx-text-fill: #000000");
        label9.setPadding(new Insets(0,0,0,25));
        Label label10 = new Label();
        label9.setStyle("-fx-font-size: 13; -fx-text-fill: #000000");
        JFXButton btn2 = new JFXButton();
        btn2.setStyle("-fx-background-color: #5CDB95; -fx-text-fill: #05386b");
        btn2.setText("SELECT FILES");
        String res;
        btn2.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                label10.setText( chooseFile());
            }
        });
        VBox.setMargin(label8, new Insets(40,0,0,0));
        hbox3.getChildren().add(label9);
        hbox3.getChildren().add(btn2);
        hbox3.getChildren().add(label10);
        VBox.setMargin(hbox3, new Insets(10,0,0,0));
        vbox.getChildren().add(label8);
        vbox.getChildren().add(hbox3);
        HBox hboxi = new HBox();
        hboxi.setAlignment(Pos.BOTTOM_RIGHT);
        JFXButton bttn = new JFXButton();
        bttn.setStyle("-fx-background-color: #05386b; -fx-text-fill: #5CDB95; -fx-font-size: 17");
        bttn.setText("SUBMIT");
        if(!isSubmitAvailable) bttn.setDisable(true);
        VBox.setMargin(hboxi, new Insets(130, 0,0,0));
        hboxi.getChildren().add(bttn);
        vbox.getChildren().add(hboxi);



        return vbox;
    }

    private String chooseFile(){
        FileChooser fc = new FileChooser();
        List<File> selectedFiles = fc.showOpenMultipleDialog(null);
        String FILE_NAME ="";
        String fileNames = "";
        if (selectedFiles != null){
            for(int i=0; i< selectedFiles.size();i++) {
                FILE_NAME = selectedFiles.get(i).getName();
                fileNames += FILE_NAME+" ,";
                auth.uploadFileStudent(this.homeworkId, this.userId, FILE_NAME, selectedFiles.get(i));

            }
        }
        return fileNames+"Uploaded Successfully";

    }


}
