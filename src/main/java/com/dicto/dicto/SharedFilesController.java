package com.dicto.dicto;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URI;

public class SharedFilesController {
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTreeTableView<Filing> tableView;
    @FXML
    private TreeTableColumn module;
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


    private User user;
    private ObservableList<Filing> data;
    private Authentication auth;

    SharedFilesController(User user, Authentication auth){
        this.user = user;
        this.auth = auth;
    }

    public void initialize(){
        id.setText(this.user.getId());
        tableView.setId("my-table");

        idd.setCellValueFactory(
                new TreeItemPropertyValueFactory<Filing,String>("id")
        );

        module.setCellValueFactory(
                new TreeItemPropertyValueFactory<Filing,String>("module")
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

    public void downloadFile(){
        if(download.getText().equals("Download Selected File")) {
            if (tableView.getSelectionModel().getSelectedItem() == null) {
                // TODO: pop up error msg
                PopUp.showDialog("Please select a file to download", "Error");
            } else {
                int id = tableView.getSelectionModel().getSelectedItem().getValue().getId();
                String fileName = tableView.getSelectionModel().getSelectedItem().getValue().getName();
                download.setText("Downloading...");
                if(auth.downloadFile(id, fileName)){
                    PopUp.showDialog("File "+fileName+" has been downloaded successfully", "Success");
                }
                else {
                    PopUp.showDialog("an error oucurred please try again later", "Error");
                }
                download.setText("Download Selected File");
            }
        }
        else{
            if (tableView.getSelectionModel().getSelectedItem() == null) {
                // TODO: pop up error msg
                PopUp.showDialog("Please select a record to watch", "Error");
            } else {
                String link = tableView.getSelectionModel().getSelectedItem().getValue().getLink();
                goToLink(link);
            }
        }
    }

    public void viewRecords(){
        // TODO: switch the colors and the text
        if(download.getText().equals("Download Selected File")) {
            download.setText("Watch Selected record");
            view.setText("View Shared Files");
            // TODO: Load the records table
            name.setText("Record Duration");
            type.setText("Type of record");
            viewRecord();
        }
        else{
            download.setText("Download Selected File");
            view.setText("View Records");
            // TODO: Load the files table
            name.setText("File name");
            type.setText("Type of document");
            viewFiles();
        }

    }

    private void viewFiles(){
        int classroom_id = auth.getClassroomId(this.user);
        data = auth.getSharedFiles(classroom_id);
        // build tree
        TreeItem<Filing> root = new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren);

        tableView.setRoot(root);
        tableView.setShowRoot(false);
    }
    private void viewRecord(){
        int classroom_id = auth.getClassroomId(this.user);
        data = auth.getRecords(classroom_id);
        // build tree
        TreeItem<Filing> root = new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren);

        tableView.setRoot(root);
        tableView.setShowRoot(false);
    }
    public void goToLink(String link){
        try {
            URI uri= new URI(link);

            java.awt.Desktop.getDesktop().browse(uri);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


}
