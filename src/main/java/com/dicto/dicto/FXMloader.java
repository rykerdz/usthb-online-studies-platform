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
    public Pane getSettingsPage(User user, Authentication auth){

        try{
            URL fileUrl = StudentScene.class.getResource("settings.fxml");
            if(fileUrl == null) throw new FileNotFoundException("This file doesn't exist");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(fileUrl);
            creators.put(SettingsController.class, new Callable<SettingsController>() {

                @Override
                public SettingsController call() throws Exception {
                    return new SettingsController(user, auth);
                }

            });

            bordir(loader);


            view = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }
    public Pane getQuestionsPage(User user, Authentication auth){

        try{
            URL fileUrl = StudentScene.class.getResource("askTeachers.fxml");
            if(fileUrl == null) throw new FileNotFoundException("This file doesn't exist");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(fileUrl);
            creators.put(AskTeachersController.class, new Callable<AskTeachersController>() {

                @Override
                public AskTeachersController call() throws Exception {
                    return new AskTeachersController(user, auth);
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
        FXMLLoader fxmlLoader = new FXMLLoader(StudentScene.class.getResource("detailedHomework.fxml"));
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

    public void openQuestionDetlaiedView(int id1, String id2, String teacherName, Authentication auth){
        FXMLLoader fxmlLoader = new FXMLLoader(StudentScene.class.getResource("detailedQuestion.fxml"));
        Scene scene = null;
        creators.put(QuestionDetailsController.class, new Callable<QuestionDetailsController>() {

            @Override
            public QuestionDetailsController call() throws Exception {
                return new QuestionDetailsController(id1, id2, teacherName, auth);
            }

        });

        bordir(fxmlLoader);
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Question details");
        stage.setScene(scene);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.show();
    }

    public void openAskQuestionView(User user, Authentication auth){
        FXMLLoader fxmlLoader = new FXMLLoader(StudentScene.class.getResource("askQuestionPopUp.fxml"));
        Scene scene = null;
        creators.put(QuestionAskController.class, new Callable<QuestionAskController>() {

            @Override
            public QuestionAskController call() throws Exception {
                return new QuestionAskController(user, auth);
            }

        });

        bordir(fxmlLoader);
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Ask a question");
        stage.setScene(scene);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.show();
    }

    public void openTeacherScene(User teacher, Authentication auth){
        FXMLLoader fxmlLoader = new FXMLLoader(StudentScene.class.getResource("teacher_scene.fxml"));
        Scene scene = null;
        creators.put(TeacherScene.class, new Callable<TeacherScene>() {

            @Override
            public TeacherScene call() throws Exception {
                return new TeacherScene(teacher, auth);
            }

        });

        bordir(fxmlLoader);
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("USTHB E-LEARNING PLATFORM - TEACHER");
        stage.setScene(scene);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.show();
    }
    public Pane getAnnounsTeacherPage(User user, Authentication auth){

        try{
            URL fileUrl = StudentScene.class.getResource("announcements_teacher.fxml");
            if(fileUrl == null) throw new FileNotFoundException("This file doesn't exist");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(fileUrl);
            creators.put(AnnouncementsTeacherController.class, new Callable<AnnouncementsTeacherController>() {

                @Override
                public AnnouncementsTeacherController call() throws Exception {
                    return new AnnouncementsTeacherController(user, auth);
                }

            });

            bordir(loader);


            view = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }
    public Pane getMeetingsTeacherPage(User user, Authentication auth){

        try{
            URL fileUrl = StudentScene.class.getResource("OnlineMeetingsTeacher.fxml");
            if(fileUrl == null) throw new FileNotFoundException("This file doesn't exist");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(fileUrl);
            creators.put(OnlineMeetingsTeacherController.class, new Callable<OnlineMeetingsTeacherController>() {

                @Override
                public OnlineMeetingsTeacherController call() throws Exception {
                    return new OnlineMeetingsTeacherController(user, auth);
                }

            });

            bordir(loader);


            view = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }

    public void openModifyMeeting(User user, int id, Authentication auth){
        FXMLLoader fxmlLoader = new FXMLLoader(StudentScene.class.getResource("modify_meeting.fxml"));
        Scene scene = null;
        creators.put(ModifyMeetingController.class, new Callable<ModifyMeetingController>() {

            @Override
            public ModifyMeetingController call() throws Exception {
                return new ModifyMeetingController(user, id, auth);
            }

        });

        bordir(fxmlLoader);
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Modify meeting");
        stage.setScene(scene);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.show();
    }
    public Pane getFilesAndRecordsTeacher(User user, Authentication auth){

        try{
            URL fileUrl = StudentScene.class.getResource("sharedFilesTeacher.fxml");
            if(fileUrl == null) throw new FileNotFoundException("This file doesn't exist");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(fileUrl);
            creators.put(SharedFilesTeacherController.class, new Callable<SharedFilesTeacherController>() {

                @Override
                public SharedFilesTeacherController call() throws Exception {
                    return new SharedFilesTeacherController(user, auth);
                }

            });

            bordir(loader);


            view = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
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
