package com.dicto.dicto;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.List;


public class UploadFileController {
    private Authentication auth;
    private User user;

    @FXML
    private Label files;
    @FXML
    private JFXRadioButton cour;
    @FXML
    private JFXRadioButton td;
    @FXML
    private JFXRadioButton tp;
    private ToggleGroup tg = new ToggleGroup();
    private Hashtable<String, File> to_upload;


    UploadFileController(User user, Authentication auth)
    {
        this.auth = auth;
        this.user = user;
    }

    public void initialize(){
        cour.setToggleGroup(tg);
        td.setToggleGroup(tg);
        tp.setToggleGroup(tg);
    }

    public void chooseFile(){
        FileChooser fc = new FileChooser();
        List<File> selectedFiles = fc.showOpenMultipleDialog(null);
        String FILE_NAME ="";
        String fileNames = "";
        to_upload = new Hashtable<>();
        if (selectedFiles != null){
            for(int i=0; i< selectedFiles.size();i++) {
                FILE_NAME = selectedFiles.get(i).getName();
                fileNames += FILE_NAME+" ,";
                to_upload.put(FILE_NAME, selectedFiles.get(i));
            }
        }
        files.setText(fileNames);
    }

    public void upload(){
        boolean show = true;
        for(String key : to_upload.keySet()){
            JFXRadioButton rb = (JFXRadioButton)tg.getSelectedToggle();
            if(rb != null){
                String type = rb.getText();
                auth.uploadFileTeacher(user.getClassroom_id(), user.getId(), key, to_upload.get(key), type);
            }
            PopUp.showDialog("Please choose a type for your files!", "Error");
            show = false;
            break;

        }
        if(show){
            PopUp.showDialog("Successfully uploaded files to the server!", "Success");
            quit();
        }

    }


    private void quit() {
        Stage stage = (Stage) tp.getScene().getWindow();
        stage.hide();
        stage.close();
    }



}
