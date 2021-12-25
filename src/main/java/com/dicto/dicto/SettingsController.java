package com.dicto.dicto;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SettingsController {
    private User user;
    private Authentication auth;

    SettingsController(User user, Authentication auth) {
        this.user = user;
        this.auth = auth;
    }

    @FXML
    private Label id;
    @FXML
    private JFXTextField matriculeTF;
    @FXML
    private JFXTextField firstNameTF;
    @FXML
    private JFXTextField lastNameTF;
    @FXML
    private JFXTextField emailTF;
    @FXML
    private JFXPasswordField passwordTF;
    @FXML
    private JFXPasswordField oldPasswordTF;
    @FXML
    private JFXPasswordField newPasswordTF;
    @FXML
    private JFXPasswordField retypeNewPasswordTF;
    @FXML
    private JFXButton saveInfoB;
    @FXML
    private JFXButton savePasswordB;


    public void initialize() {
        id.setText(user.getId());
        firstNameTF.setText(user.getFirstName());
        lastNameTF.setText(user.getLastName());
        emailTF.setText(user.getEmail());
        matriculeTF.setText(user.getId());


    }

    public void setVisible() {
        passwordTF.setDisable(false);
        saveInfoB.setDisable(false);
    }

    public void pushUpdates() {
        if (user.getEmail().equals(emailTF.getText()) && user.getFirstName().equals(firstNameTF.getText()) && user.getLastName().equals(lastNameTF.getText())) {
            PopUp.showDialog("No changes are made. aborting...", "Notice");
        } else {
            if (Data.checkName(firstNameTF.getText(), lastNameTF.getText()) && Data.checkEmail(emailTF.getText()) && Data.checkPassword_login(passwordTF.getText())) {
                if (emailTF.getText().contains("@etu.usthb.dz")) {
                    if (auth.checkPasswordAndPushUpdates(user.getId(), passwordTF.getText(), firstNameTF.getText(), lastNameTF.getText(), emailTF.getText())) {
                        PopUp.showDialog("Changes saved!", "Success");
                        user.setFirstName(firstNameTF.getText());
                        user.setLastName(lastNameTF.getText());
                        user.setEmail(emailTF.getText());
                    } else {
                        PopUp.showDialog("Wrong password!", "Error");
                    }
                } else {
                    PopUp.showDialog("Your email must contain @etu.usthb.dz since you are a student!", "Error");
                }

            } else {
                PopUp.showDialog("No special characters are allowed in the name fields, password must be atleast 8 characters long", "Error");
            }
        }
    }

    public void pushhPassword(){
        if(Data.checkPassword(newPasswordTF.getText(), retypeNewPasswordTF.getText())){
            if(Data.checkPassword_login(oldPasswordTF.getText())){
                if(auth.checkPasswordAndPushNewPassword(user.getId(), oldPasswordTF.getText(), newPasswordTF.getText())){
                    PopUp.showDialog("You have successfully changed your password!", "Success");
                }
                else{
                    PopUp.showDialog("Wrong password, Please try again!", "Error");
                }
            }
            else PopUp.showDialog("Password must be at least 8 characters long!", "Error");
        }
        else PopUp.showDialog("Passwords dont match!, note that the password must be atleast 8 charcters long contains letters and numbers", "Error");
    }
}