package com.dicto.dicto;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URI;
import java.util.List;

public class SharedFilesTeacherController {
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTreeTableView<Filing> tableView;
    @FXML
    private TreeTableColumn type;
    @FXML
    private TreeTableColumn name;
    @FXML
    private TreeTableColumn date;
    @FXML
    private TreeTableColumn idd;
    @FXML
    private Label id;
    @FXML
    private JFXButton view;
    @FXML
    private JFXButton download;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton modify;


    private User user;
    private ObservableList<Filing> data;
    private Authentication auth;

    SharedFilesTeacherController(User user, Authentication auth){
        this.user = user;
        this.auth = auth;
    }

    public void initialize(){
        id.setText(this.user.getId());
        tableView.setId("my-table");

        idd.setCellValueFactory(
                new TreeItemPropertyValueFactory<Filing,String>("id")
        );


        type.setCellValueFactory(
                new TreeItemPropertyValueFactory<Filing,String>("type")
        );

        name.setCellValueFactory(
                new TreeItemPropertyValueFactory<Filing,String>("name")
        );
        date.setCellValueFactory(
                new TreeItemPropertyValueFactory<Filing,String>("date")
        );
        viewFiles();

    }

    public void uploadFile(){
        if(download.getText().equals("Upload a new file")) {
            // TODO: upload a file
            FXMloader loader = new FXMloader();
            loader.openUploadFile(user, auth);



        }
        else{
            FXMloader loader = new FXMloader();
            loader.openUploadRecord(user, auth);

        }
    }


    public void viewRecords(){
        // TODO: switch the colors and the text
        if(download.getText().equals("Upload a new file")) {
            download.setText("Add a record");
            view.setText("View Shared Files");
            delete.setText("Delete selected record");
            // TODO: Load the records table
            name.setText("Record Duration");
            type.setText("Type of record");
            date.setText("Session of");
            modify.setVisible(true);
            viewRecord();
        }
        else{
            download.setText("Upload a new file");
            view.setText("View Records");
            delete.setText("Delete selected file");
            // TODO: Load the files table
            name.setText("File name");
            type.setText("Type of document");
            date.setText("Uploaded on");
            modify.setVisible(false);

            viewFiles();
        }

    }

    private void viewFiles(){
        data = auth.getSharedFilesTeacher(user.getClassroom_id(), user.getId());
        // build tree
        TreeItem<Filing> root = new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren);

        tableView.setRoot(root);
        tableView.setShowRoot(false);
    }
    private void viewRecord(){
        data = auth.getRecordsTeacher(user.getClassroom_id(), user.getId());
        // build tree
        TreeItem<Filing> root = new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren);

        tableView.setRoot(root);
        tableView.setShowRoot(false);
    }
    public void modifyRecord(){
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            int id = tableView.getSelectionModel().getSelectedItem().getValue().getId();
            FXMloader loader = new FXMloader();
            loader.openUpdateRecord(user, id, auth);
        }
        else {
            PopUp.showDialog("Please select an element from the table first!", "");
        }
    }

    public void delete(){
        if (tableView.getSelectionModel().getSelectedItem() != null){
            int id = tableView.getSelectionModel().getSelectedItem().getValue().getId();
            if(download.getText().equals("Upload a new file")) {
                // TODO: delete a file
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this file?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    //do stuff
                    if(auth.deleteFile(id)){
                        PopUp.showDialog("Selected file has been deleted successfully!", "Success");

                    }
                    else {
                        PopUp.showDialog("an error occured try again later","error");
                    }
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this record?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    //do stuff
                    if(auth.deleteRecord(id)){
                        PopUp.showDialog("Selected record has been deleted successfully!", "Success");

                    }
                    else {
                        PopUp.showDialog("an error occured try again later","error");
                    }
                }

            }
        }
        else {
            PopUp.showDialog("Please select an element from the table first!", "");
        }

    }


}
