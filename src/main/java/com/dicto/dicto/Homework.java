package com.dicto.dicto;

public class Homework {

    private String body;
    private String teacherName;
    private String duration;



    private OneReallySimpleFile file;
    private String title;
    private String publishDate;
    private String dueDate;

    public Homework(String body, String teacherName, String duration, OneReallySimpleFile file, String title, String publishDate, String dueDate) {
        this.body = body;
        this.teacherName = teacherName;
        this.duration = duration;
        this.file = file;
        this.title = title;
        this.publishDate = publishDate;
        this.dueDate = dueDate;
    }

    public OneReallySimpleFile getFile() {
        return file;
    }

    public void setFile(OneReallySimpleFile file) {
        this.file = file;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
