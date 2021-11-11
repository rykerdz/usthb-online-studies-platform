package com.dicto.dicto;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RegisterController {
    @FXML
    private JFXComboBox group_cb;
    @FXML
    private JFXComboBox section_cb;
    @FXML
    private JFXComboBox level_cb;
    @FXML
    private JFXTextField email;

    public void disableGroupComboBox(){
        String mail = email.getText();
        if (mail.contains("etu.usthb.dz")) {
            group_cb.setVisible(true);
        }
        else{
            group_cb.setVisible(false);
        }
    }
    @FXML
    public void initialize() {
        String groups[] = {"1", "2", "3"};
        group_cb.setItems(FXCollections.observableArrayList(groups));
        String levels[] = {"L3-ISIL", "L3-ACAD"};
        level_cb.setItems(FXCollections.observableArrayList(levels));

    }
    public void initComboBox(){
        String current_level = level_cb.getEditor().getText();
        System.out.println(current_level);
        if (current_level.contains("ISIL")){
            String[] sections = { "A", "B"};
            section_cb.setItems(FXCollections.observableArrayList(sections));

        }
        else {
            String[] sections = { "A", "B", "C"};
            section_cb.setItems(FXCollections.observableArrayList(sections));
        }
    }



}
