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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;


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

    public void sign_in(ActionEvent event) throws IOException {
        // data
        String username = email.getText();
        String password = pass.getText();
        Data data = new Data(username, password);
        User user = data.checkData_login();
        msg_label.setTextFill(Color.color(1, 0, 0));
        msg_label.setText(user.getNeed2passAMSG());
        // end data



        Map<Class, Callable<?>> creators = new HashMap<>();
        FXMLLoader loader = new FXMLLoader();
        if (user.isTeacher()){
            creators.put(PreTeacherScene.class, new Callable<PreTeacherScene>() {

                @Override
                public PreTeacherScene call() throws Exception {
                    return new PreTeacherScene(user);
                }

            });
            loader.setLocation(Objects.requireNonNull(getClass().getResource("pre_teacher_scene.fxml")));

        }
        else {

            creators.put(StudentScene.class, new Callable<StudentScene>() {

                @Override
                public StudentScene call() throws Exception {
                    return new StudentScene(user);
                }
            });

            loader.setLocation(Objects.requireNonNull(getClass().getResource("student_scene.fxml")));
        }

        loader.setControllerFactory(new Callback<Class<?>, Object>() {

            @Override
            public Object call(Class<?> param) {
                Callable<?> callable = creators.get(param);
                if (callable == null) {
                    try {
                        // default handling: use no-arg constructor
                        return param.newInstance();
                    } catch (InstantiationException | IllegalAccessException ex) {
                        throw new IllegalStateException(ex);
                    }
                } else {
                    try {
                        return callable.call();
                    } catch (Exception ex) {
                        throw new IllegalStateException(ex);
                    }
                }
            }
        });
        Parent registerParent = loader.load();
        Scene registerScene = new Scene(registerParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        //StudentScene controller = loader.getController();





        if(!user.isNull()){
            //window.setUserData(user);
            //controller.getData(user);
            window.setScene(registerScene);
            window.show();

        }



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
    public void showStudentWindow() throws IOException
    {

    }

}