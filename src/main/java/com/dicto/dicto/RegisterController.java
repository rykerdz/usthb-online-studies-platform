package com.dicto.dicto;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;


public class RegisterController<textFormatter> {
    @FXML
    private JFXComboBox section_cb;
    @FXML
    private JFXComboBox level_cb;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField fname;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField password_confirm;
    @FXML
    private JFXDatePicker b_day;
    @FXML
    private Label msg_label;
    @FXML
    private JFXTextField matricule;

    public void disableGroupComboBox(){
        String mail = email.getText();
        if (mail.contains("etu.usthb.dz")) {
            level_cb.setVisible(true);
            section_cb.setVisible(true);

        }
        else{
            level_cb.setVisible(false);
            section_cb.setVisible(false);
        }
    }


    @FXML
    public void initialize() {

        String levels[] = {"L3-ISIL", "L3-ACAD"};
        level_cb.setItems(FXCollections.observableArrayList(levels));
        level_cb.setVisible(false);
        section_cb.setVisible(false);
        matricule.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*") && matricule.getText().length()<12)
                return null;
            else
                return c;
        }
        ));

    }
    public void initComboBox(){
        String current_level = level_cb.getValue().toString();
        if (current_level.contains("ISIL")){
            String[] sections = { "A", "B"};
            section_cb.setItems(FXCollections.observableArrayList(sections));

        }
        else {
            String[] sections = { "A", "B", "C"};
            section_cb.setItems(FXCollections.observableArrayList(sections));
        }
    }

    public void setEmailAdress(){
        String name_s = name.getText();
        String fname_s = fname.getText();
        email.setText(name_s+'.'+fname_s);
    }

    public void showHelloWindow(ActionEvent event) throws IOException
    {
        Parent registerParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        Scene registerScene = new Scene(registerParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(registerScene);
        window.show();
    }

    public void  check_data(){
        // getting the data
        Data data = null;
        String error_msg;
        String id = matricule.getText();
        String name_s = name.getText();
        String fname_s = fname.getText();
        String email_s = email.getText();
        String pass1 = password.getText();
        String pass2 = password_confirm.getText();
        LocalDate birth_day = b_day.getValue();
        if(name_s.equals("")) {
            msg_label.setText("Name can't be empty!");
        }
        else{
            if(fname_s.equals("")) {
                msg_label.setText("Family name can't be empty!");
            }
            else{
                if(email_s.equals("")) {
                    msg_label.setText("Email field can't be empty!");
                }
                else{
                    if(pass1.equals("")) {
                        msg_label.setText("Password field can't be empty!");
                    }
                    else{
                        if(pass2.equals("")) {
                            msg_label.setText("Password confirmation field can't be empty!");
                        }
                        else{
                            if(birth_day== null) {
                                msg_label.setText("Birth Day field can't be empty!");
                            }
                            else{
                                if(level_cb.isVisible()){
                                    if(level_cb.getValue()==null) msg_label.setText("Please choose a Level");
                                    else{
                                        String level = level_cb.getValue().toString();
                                        if(section_cb.getValue()==null) msg_label.setText("Please choose a section");
                                        else {
                                            String section = section_cb.getValue().toString();
                                            data = new Data(id ,name_s, fname_s, email_s, pass1, pass2, birth_day, level, section);
                                        }
                                    }
                                }
                                else{
                                    data = new Data(id, name_s, fname_s, email_s, pass1, pass2, birth_day);
                                }
                                if(data != null) {
                                    error_msg = data.checkData();
                                    if (error_msg.contains("successfully")) msg_label.setTextFill(Color.color(0,1,0.2));
                                    msg_label.setText(error_msg);
                                }



                            }
                        }

                    }
                }

            }
        }



    }





}
