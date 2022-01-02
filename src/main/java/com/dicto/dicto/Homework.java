package com.dicto.dicto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import java.time.LocalDate;
import java.time.LocalTime;

public class Homework extends RecursiveTreeObject<Homework> {

    private String body;
    private String teacherName;
    private String duration;

    private int id;
    private LocalTime time;
    private LocalDate date;

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

    public Homework(String title, String body, LocalTime time, LocalDate date, String duration) {
        this.body = body;
        this.duration = duration;
        this.time = time;
        this.date = date;
        this.title = title;
    }

    public Homework(int id, String title, String publishDate, String dueDate){
        this.id = id;
        this.title = title;
        this.publishDate = publishDate;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
