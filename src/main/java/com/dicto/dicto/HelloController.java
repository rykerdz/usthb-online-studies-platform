package com.dicto.dicto;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class HelloController {
    @FXML
    private Hyperlink hyperlink;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField pass;
    @FXML
    private Label msg_label;
    @FXML
    protected void openlink() throws URISyntaxException {
        try {
            Desktop.getDesktop().browse(new URI("www.usthb.dz"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void quit(){

        System.exit(0);
    }

    public void sign_in(){
        String college_domain = "usthb.dz";
        String user = email.getText();
        String password = pass.getText();
        if (validate((user))){
            if (user.contains(college_domain)){
                if (password.length()>6){
                    //TODO: send the SQL request to check user

                }
                else{
                    msg_label.setText("Error: Password must be at least 6 characters long!");
                    msg_label.setTextFill(Color.color(1,0,0.2));
                }

            }
            else{
                msg_label.setText("Error: Please use your usthb email @etu./@ usthb.dz ");
                msg_label.setTextFill(Color.color(1,0,0.2));

            }

        }
        else{
            msg_label.setText("Error: INVALID Email!");
            msg_label.setTextFill(Color.color(1,0,0.2));

        }
    
    }
    // email checking ...
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }


    public void showRegisterWindow(ActionEvent event) throws IOException
    {
        Parent registerParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register.fxml")));
        Scene registerScene = new Scene(registerParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(registerScene);
        window.show();
    }
}