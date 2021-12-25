package com.dicto.dicto;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;


public class PopUp {
    @FXML
    private Label text = new Label();
    @FXML
    private static AnchorPane pane = new AnchorPane();
    private static Stage primaryStage;

    public static void showDialog(String message, String type){
        FXMLLoader loader = new FXMLLoader();
        try {
            primaryStage = new Stage();
            pane = loader.load(PopUp.class.getResource("popUp.fxml").openStream());
            PopUp diag = (PopUp) loader.getController();
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.setTitle(type);
            primaryStage.show();
            diag.setMessage(message);


        }catch(Exception e ) {
            e.printStackTrace();
        }
    }

    @FXML
    private void quit() {
        Stage stage = (Stage) text.getScene().getWindow();
        stage.hide();
        stage.close();
    }
    private void setMessage(String msg){
        text.setText(msg);
    }
}
