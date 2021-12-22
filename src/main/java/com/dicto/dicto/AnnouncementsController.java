package com.dicto.dicto;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class AnnouncementsController {
    private User user;
    private Authentication auth;
    AnnouncementsController(User user, Authentication auth){
        this.user = user;
        this.auth = auth;
    }

    @FXML
    private Label title1, text1, title2, text2, title3, text3, title4, text4, title5, text5, title6, text6, title7, text7;
    @FXML
    private Label id_talk;
    @FXML
    private Label id;


    public void initialize(){
        title1.setVisible(false); text1.setVisible(false);
        title2.setVisible(false); text2.setVisible(false);
        title3.setVisible(false); text3.setVisible(false);
        title4.setVisible(false); text4.setVisible(false);
        title5.setVisible(false); text5.setVisible(false);
        title6.setVisible(false); text6.setVisible(false);
        title7.setVisible(false); text7.setVisible(false);
        id.setText(user.getId());
        parseData();

    }

    public void parseData(){
        int classroom_id = auth.getClassroomId(this.user);
        Hashtable<String, Body> data = auth.getAnnouncements(classroom_id);
        Set<String> setOfKeys = data.keySet();

        // Creating an Iterator object to
        // iterate over the given Hashtable
        Iterator<String> itr = setOfKeys.iterator();
        if(itr.hasNext()){
            String key = itr.next();
            title1.setVisible(true);
            text1.setVisible(true);
            title1.setText(key+" @ "+data.get(key).getTimestamp());
            text1.setText(data.get(key).getText());
        }
        if(itr.hasNext()){
            String key = itr.next();
            title2.setVisible(true);
            text2.setVisible(true);
            title2.setText(key+" @ "+data.get(key).getTimestamp());
            text2.setText(data.get(key).getText());
        }
        if(itr.hasNext()){
            String key = itr.next();
            title3.setVisible(true);
            text3.setVisible(true);
            title3.setText(key+" @ "+data.get(key).getTimestamp());
            text3.setText(data.get(key).getText());
        }
        if(itr.hasNext()){
            String key = itr.next();
            title4.setVisible(true);
            text4.setVisible(true);
            title4.setText(key+" @ "+data.get(key).getTimestamp());
            text4.setText(data.get(key).getText());
        }
        if(itr.hasNext()){
            String key = itr.next();
            title5.setVisible(true);
            text5.setVisible(true);
            title5.setText(key+" @ "+data.get(key).getTimestamp());
            text5.setText(data.get(key).getText());
        }
        if(itr.hasNext()){
            String key = itr.next();
            title6.setVisible(true);
            text6.setVisible(true);
            title6.setText(key+" @ "+data.get(key).getTimestamp());
            text6.setText(data.get(key).getText());
        }
        if(itr.hasNext()){
            String key = itr.next();
            title7.setVisible(true);
            text7.setVisible(true);
            title7.setText(key+" @ "+data.get(key).getTimestamp());
            text7.setText(data.get(key).getText());
        }

    }

}
