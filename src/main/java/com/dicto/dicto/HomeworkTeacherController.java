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

public class HomeworkTeacherController {
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTreeTableView<Homework> tableView;
    @FXML
    private TreeTableColumn title;
    @FXML
    private TreeTableColumn published;
    @FXML
    private TreeTableColumn due;
    @FXML
    private TreeTableColumn idd;
    @FXML
    private Label id;
    @FXML
    private JFXButton add;
    @FXML
    private JFXButton modify;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton switcher;
    private int homework_id;

    private User user;
    private ObservableList<Homework> data;
    private Authentication auth;

    HomeworkTeacherController(User user, Authentication auth){
        this.user = user;
        this.auth = auth;
    }

    public void initialize(){
        id.setText(this.user.getId());
        tableView.setId("my-table");

        idd.setCellValueFactory(
                new TreeItemPropertyValueFactory<Homework,String>("id")
        );

        title.setCellValueFactory(
                new TreeItemPropertyValueFactory<Homework,String>("title")
        );

        published.setCellValueFactory(
                new TreeItemPropertyValueFactory<Homework,String>("publishDate")
        );
        due.setCellValueFactory(
                new TreeItemPropertyValueFactory<Homework,String>("dueDate")
        );
        viewHomeworks();

    }

    public void addHomework(){
        // TODO: Add a new homework
        if(switcher.getText().equals("See students solution")) {

            FXMloader loader = new FXMloader();
            loader.openAddHomework(user, auth);
        }
        else {
            if(tableView.getSelectionModel().getSelectedItem() != null) {
                add.setText("Download solution...");
                if(auth.downloadFileTeacher(homework_id, tableView.getSelectionModel().getSelectedItem().getValue().getTitle(), tableView.getSelectionModel().getSelectedItem().getValue().getPublishDate())){
                    PopUp.showDialog("Student: "+tableView.getSelectionModel().getSelectedItem().getValue().getPublishDate()+" solution has been downloaded to your /Downloads directory", "Success");
                }
                else {
                    PopUp.showDialog("An error occurred , please try again", "Error");
                }
                add.setText("Download solution");
            }
        }
    }

    public void seeSolutions(){
        if(switcher.getText().equals("See students solution")) {
            if(tableView.getSelectionModel().getSelectedItem() != null) {
                add.setText("Download solution");
                modify.setVisible(false);
                delete.setVisible(false);
                switcher.setText("View homeworks");
                title.setText("Name");
                published.setText("Uploaded on:");
                due.setText("Note");

                viewSolutions();
            }
            else {
                PopUp.showDialog("Select a homework first!", "Error");
            }
        }
        else{
            add.setText("Assign a new homework");
            modify.setVisible(true);
            delete.setVisible(true);
            switcher.setText("See students solution");
            title.setText("Title");
            published.setText("Published on");
            due.setText("Due on");
            viewHomeworks();

        }

    }


    private void viewHomeworks(){
        data = auth.getHomeworksTeacher(user.getClassroom_id(), user.getId());
        // build tree
        TreeItem<Homework> root = new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren);

        tableView.setRoot(root);
        tableView.setShowRoot(false);
    }
    private void viewSolutions() {

        homework_id = tableView.getSelectionModel().getSelectedItem().getValue().getId();
        data = auth.getHomeworksSolutions(homework_id);
        // build tree
        TreeItem<Homework> root = new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren);

        tableView.setRoot(root);
        tableView.setShowRoot(false);
    }
    public void modifyHomework(){
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            int id = tableView.getSelectionModel().getSelectedItem().getValue().getId();
            // TODO: open modify homework window
            FXMloader loader = new FXMloader();
            loader.openUpdateHomework(user, id, auth);
        }
        else {
            PopUp.showDialog("Please select an element from the table first!", "");
        }
    }

    public void delete(){
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            int id = tableView.getSelectionModel().getSelectedItem().getValue().getId();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel this homework?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                // TODO: change delete file to delete homework
                if (auth.deleteHomework(id)) {
                    PopUp.showDialog("Selected homework has been canceled successfully!", "Success");

                } else {
                    PopUp.showDialog("an error occured try again later", "error");
                }
            }
        }
        else {
            PopUp.showDialog("Please select an element from the table first!", "");
        }

    }


}
