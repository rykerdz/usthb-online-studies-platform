package com.dicto.dicto;

import java.sql.Timestamp;

public class Body {
    private String text, timestamp, text2;
    private int id;
    private boolean dueToday;

    Body(String text, String timestamp){
        this.text = text;
        this.timestamp = timestamp;
    }
    Body(String text1, String text2, String text3){
        this.text = text1;
        this.timestamp = text2;
        this.text2 = text3;
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
}
