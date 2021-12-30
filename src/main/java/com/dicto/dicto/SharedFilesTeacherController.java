package com.dicto.dicto;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URI;

public class SharedFilesTeacherController {
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
    @FXML
    private JFXButton modify;
    @FXML
    private JFXButton delete;


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
            } else {
                int id = tableView.getSelectionModel().getSelectedItem().getValue().getId();
                String fileName = tableView.getSelectionModel().getSelectedItem().getValue().getName();
            }
        }
        else{
            if (tableView.getSelectionModel().getSelectedItem() == null) {
                // TODO: pop up error msg
            } else {
                String link = tableView.getSelectionModel().getSelectedItem().getValue().getLink();
                goToLink(link);
            }
        }
    }

    public void viewRecords(){
        // TODO: switch the colors and the text
        if(download.getText().equals("Upload a new file")) {
            download.setText("Add a record");
            view.setText("View Shared Files");
            modify.setText("Modify selected record");
            delete.setText("Delete selected record");
            // TODO: Load the records table
            name.setText("Record Duration");
            type.setText("Type of record");
            viewRecord();
        }
        else{
            download.setText("Upload a new file");
            view.setText("View Records");
            modify.setText("Modify selected file");
            delete.setText("Delete selected file");
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
