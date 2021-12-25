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

public class AskTeachersController {
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTreeTableView<Question> tableView;
    @FXML
    private TreeTableColumn module;
    @FXML
    private TreeTableColumn askedOn;
    @FXML
    private TreeTableColumn teacherName;
    @FXML
    private TreeTableColumn subject;
    @FXML
    private TreeTableColumn idd;
    @FXML
    private TreeTableColumn answered;
    @FXML
    private Label id;
    @FXML
    private JFXButton view;
    @FXML
    private JFXButton add;


    private User user;
    private ObservableList<Question> data;
    private Authentication auth;

    AskTeachersController(User user, Authentication auth){
        this.user = user;
        this.auth = auth;
    }

    public void initialize(){
        id.setText(this.user.getId());
        tableView.setId("my-table");

        idd.setCellValueFactory(
                new TreeItemPropertyValueFactory<Question,String>("id")
        );

        module.setCellValueFactory(
                new TreeItemPropertyValueFactory<Question,String>("module")
        );

        askedOn.setCellValueFactory(
                new TreeItemPropertyValueFactory<Question,String>("askedOn")
        );

        teacherName.setCellValueFactory(
                new TreeItemPropertyValueFactory<Question,String>("teacherName")
        );
        subject.setCellValueFactory(
                new TreeItemPropertyValueFactory<Question,String>("subject")
        );
        answered.setCellValueFactory(
                new TreeItemPropertyValueFactory<Question,String>("answered")
        );
        viewQuestions();

    }


    private void viewQuestions(){
        data = auth.getQuestions(user.getId());
        // build tree
        TreeItem<Question> root = new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren);

        tableView.setRoot(root);
        tableView.setShowRoot(false);
    }

    public void seeQuestionDetails(){
        int id = tableView.getSelectionModel().getSelectedItem().getValue().getId();
        String teacherName = tableView.getSelectionModel().getSelectedItem().getValue().getTeacherName();
        FXMloader lol = new FXMloader();
        lol.openQuestionDetlaiedView(id, this.user.getId(),teacherName, this.auth);
    }

    public void askQuestion(){
        FXMloader lol = new FXMloader();
        lol.openAskQuestionView(this.user, this.auth);
    }


}
