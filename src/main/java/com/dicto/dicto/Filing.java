package com.dicto.dicto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class Filing extends RecursiveTreeObject<Filing> {
    private String module, type, name, date, link;
    private int id;

    Filing(int id, String module, String type, String name, String date){
        this.id = id;
        this.module = module;
        this.type = type;
        this.name = name;
        this.date = date;
    }


    public void setName(String name){ this.name = name;}
    public void setModule(String module){ this.module = module;}
    public void setType(String type){ this.type = type;}

    public void setLink(String link) {
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setDate(String date) {this.date = date;}

    public String getName(){ return this.name;}
    public String getModule(){ return this.module;}
    public String getType() {return this.type;}

    public String getDate() {return date;}

    public String getLink() {
        return link;
    }

    public void setId(int id) {
        this.id = id;
    }
}
