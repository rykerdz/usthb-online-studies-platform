package com.dicto.dicto;

import java.time.LocalDate;
import java.util.Locale;

public class User {
    private String id, firstName, lastName, email, password, level, section;
    private LocalDate birthDay;
    private int classroom_id;
    private boolean isTeacher = false;
    private boolean isNull;
    private String need2passAMSG;

    public User(String matricule, String name, String fname, String email_s, String pass1, LocalDate bdate,String level_s, String section_s) {
        // constructor for student
        id = matricule;
        firstName = name;
        lastName = fname;
        email = email_s;
        password = pass1;
        section = section_s;
        level = level_s;
        birthDay = bdate;
        isNull = false;

        classroom_id = getClassroomId();


    }

    public User(String matricule, String name, String fname, String email_s, String pass1, LocalDate bdate) {
        // constructor for teacher
        id = matricule;
        firstName = name;
        lastName = fname;
        email = email_s;
        password = pass1;
        birthDay = bdate;
        isTeacher = true;
        isNull = false;
    }
    public User(){
        isNull = true;

    }

    //setters
    public void setId(String id) {this.id = id;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
    public void setBirthDay(LocalDate birthDay) {this.birthDay = birthDay;}
    public void setTeacher(boolean isTeacher){this.isTeacher = isTeacher;}
    public void setNeed2passAMSG(String msg){this.need2passAMSG = msg;}
    // getters
    public String getId() {return this.id;}
    public String getFirstName() {return this.firstName;}
    public String getLastName() {return this.lastName;}
    public String getEmail() {return this.email;}
    public String getPassword() {return this.password;}
    public LocalDate getBirthDay() {return this.birthDay;}
    public boolean isTeacher() {return isTeacher;}
    public String getLevel(){if(!this.isTeacher()) return this.level; else return null;}
    public String getSection(){if(!this.isTeacher()) return this.section; else return null;}
    public String getNeed2passAMSG(){return this.need2passAMSG;}
    public boolean isNull(){ return this.isNull;}

    private int getClassroomId(){
        Authentication auth = new Authentication();
        int classroom_id = auth.getClassroomId(this);
        return classroom_id;
    }
    public String getFullNameDecorated(){
        return this.lastName.toUpperCase() + " "+ firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
    }

    public int getClassroom_id(){
        return this.classroom_id;
    }
    public void setClassroom_id(int classroom_id){
        this.classroom_id = classroom_id;
    }
}
