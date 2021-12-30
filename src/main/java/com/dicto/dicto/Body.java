package com.dicto.dicto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Body {
    private String text;
    private String timestamp;
    private String text2;
    private String text4;
    private int id;
    private boolean dueToday;
    private LocalDate date;
    private LocalTime starttime, endtime;

    Body(String text, String timestamp){
        this.text = text;
        this.timestamp = timestamp;
    }
    Body(String text1, String text2, String text3){
        this.text = text1;
        this.timestamp = text2;
        this.text2 = text3;
    }
    Body(int id, String text1, String text2, String text3, String text4){
        this.id = id;
        this.text = text1;
        this.timestamp = text2;
        this.text2 = text3;
        this.text4 = text4;
    }

    public Body(String text, String text2, LocalTime starttime, LocalTime endtime, LocalDate date) {
        this.text = text;
        this.text2 = text2;
        this.starttime = starttime;
        this.endtime = endtime;
        this.date = date;
    }

    Body(String text1, String text2, int id, boolean dueToday){
        this.text = text1;
        this.timestamp = text2;
        this.id = id;
        this.dueToday = dueToday;
    }


    public void setText(String text){ this.text = text;}
    public void setTimestamp(String timestamp){ this.timestamp = timestamp;}
    public void setText2(String text2){ this.text2 = text2;}

    public void setId(int id) {
        this.id = id;
    }

    public void setDueToday(boolean dueToday) {
        this.dueToday = dueToday;
    }

    public String getText(){ return this.text;}
    public String getTimestamp(){ return  this.timestamp;}
    public String getText2() { return this.text2;}

    public int getId() {
        return id;
    }

    public boolean isDueToday() {
        return dueToday;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public LocalTime getStarttime() {
        return starttime;
    }

    public void setStarttime(LocalTime starttime) {
        this.starttime = starttime;
    }

    public LocalTime getEndtime() {
        return endtime;
    }

    public void setEndtime(LocalTime endtime) {
        this.endtime = endtime;
    }



    public String getText4() {
        return text4;
    }

    public void setText4(String text4) {
        this.text4 = text4;
    }



}
