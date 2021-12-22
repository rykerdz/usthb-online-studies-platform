package com.dicto.dicto;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class FXMloader {
    private Pane view;
    private static Map<Class, Callable<?>> creators = new HashMap<>();


    public Pane getAnnouPage(User user, Authentication auth){

        try{
            URL fileUrl = StudentScene.class.getResource("announcements.fxml");
            if(fileUrl == null) throw new FileNotFoundException("This file doesn't exist");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(fileUrl);
            creators.put(AnnouncementsController.class, new Callable<AnnouncementsController>() {

                @Override
                public AnnouncementsController call() throws Exception {
                    return new AnnouncementsController(user, auth);
                }

            });

            bordir(loader);


            view = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }
    public Pane getMeetingsPage(User user, Authentication auth){

        try{
            URL fileUrl = StudentScene.class.getResource("onlineMeetings.fxml");
            if(fileUrl == null) throw new FileNotFoundException("This file doesn't exist");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(fileUrl);
            creators.put(OnlineMeetingsController.class, new Callable<OnlineMeetingsController>() {

                @Override
                public OnlineMeetingsController call() throws Exception {
                    return new OnlineMeetingsController(user, auth);
                }

            });

            bordir(loader);


            view = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }
    public Pane getSharedFilesPage(User user, Authentication auth){

        try{
            URL fileUrl = StudentScene.class.getResource("sharedFiles.fxml");
            if(fileUrl == null) throw new FileNotFoundException("This file doesn't exist");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(fileUrl);
            creators.put(SharedFilesController.class, new Callable<SharedFilesController>() {

                @Override
                public SharedFilesController call() throws Exception {
                    return new SharedFilesController(user, auth);
                }

            });

            bordir(loader);


            view = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }

    public Pane getHomeworkPage(User user, Authentication auth){

        try{
            URL fileUrl = StudentScene.class.getResource("homework.fxml");
            if(fileUrl == null) throw new FileNotFoundException("This file doesn't exist");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(fileUrl);
            creators.put(HomeworkController.class, new Callable<HomeworkController>() {

                @Override
                public HomeworkController call() throws Exception {
                    return new HomeworkController(user, auth);
                }

            });

            bordir(loader);


            view = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }

    public void openDetlaiedView(int id1, String id2, Authentication auth){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("detailedHomework.fxml"));
        Scene scene = null;
        creators.put(HomeworkDetailsController.class, new Callable<HomeworkDetailsController>() {

            @Override
            public HomeworkDetailsController call() throws Exception {
                return new HomeworkDetailsController(id1, id2, auth);
            }

        });

        bordir(fxmlLoader);
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Homework details");
        stage.setScene(scene);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.show();
    }



    private void bordir(FXMLLoader loader){
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
    }
}
