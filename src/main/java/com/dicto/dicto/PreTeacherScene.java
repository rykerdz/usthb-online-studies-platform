package com.dicto.dicto;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Hashtable;


public class PreTeacherScene {
    private User user;
    private Authentication auth = new Authentication();
    private Hashtable<String, Integer> data;

    @FXML
    private Label name;
    @FXML
    private JFXComboBox<String> combo;



    public PreTeacherScene(User user){
        this.user = user;
    }

    public void initialize(){
        name.setText(user.getFullNameDecorated());
        data = auth.getClassrooms(user.getId());
        ObservableList<String> items = FXCollections.observableArrayList(data.keySet());
        combo.setItems(items);

    }

    public void openClassroom(){
        if (combo.getValue() != null){
            // TODO: open teacher scene with selected classroom , push teacher_id, classroom_id
            int classroom_id = data.get(combo.getValue());
            user.setClassroom_id(classroom_id);
            FXMloader loader = new FXMloader();
            loader.openTeacherScene(user, auth);
            quit();
        }
    }
    private void quit() {
        Stage stage = (Stage) name.getScene().getWindow();
        stage.hide();
        stage.close();
    }


}