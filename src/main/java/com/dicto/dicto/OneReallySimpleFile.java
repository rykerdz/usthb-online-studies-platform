package com.dicto.dicto;

public class OneReallySimpleFile {
    private int id;
    private String type;
    private String filename;

    OneReallySimpleFile(int id, String type, String filename){
        this.id = id;
        this.type = type;
        this.filename = filename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


}
