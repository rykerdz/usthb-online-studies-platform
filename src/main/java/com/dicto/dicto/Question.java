package com.dicto.dicto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class Question extends RecursiveTreeObject<Question> {
    private int id;
    private String module;
    private String teacherName;
    private String askedOn;
    private String subject;
    private String answered;


    private String body;
    private String response;
    private String respondedOn;

    public Question(int id, String module, String teacherName, String askedOn, String subject, String answered) {
        this.id = id;
        this.module = module;
        this.teacherName = teacherName;
        this.askedOn = askedOn;
        this.subject = subject;
        this.answered = answered;
    }
    public Question(String askedOn, String respondedOn, String subject, String body, String response) {
        this.body = body;
        this.respondedOn = respondedOn;
        this.askedOn = askedOn;
        this.subject = subject;
        this.response = response;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getAskedOn() {
        return askedOn;
    }

    public void setAskedOn(String askedOn) {
        this.askedOn = askedOn;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAnswered() {
        return answered;
    }

    public void setAnswered(String answered) {
        this.answered = answered;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getRespondedOn() {
        return respondedOn;
    }

    public void setRespondedOn(String respondedOn) {
        this.respondedOn = respondedOn;
    }

}
