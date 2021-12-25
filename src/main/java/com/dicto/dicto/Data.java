package com.dicto.dicto;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Data {
    private String id, firstName, lastName, email, password, password_confirmation, level="none", section;
    private LocalDate birth_date;

    public Data(String matricule, String name, String fname, String email_s, String pass1, String pass2, LocalDate bdate,String level_s, String section_s) {
        // constructor for student
        id = matricule;
        firstName = name;
        lastName = fname;
        email = email_s;
        password = pass1;
        password_confirmation = pass2;
        section = section_s;
        level = level_s;
        birth_date = bdate;

    }

    public Data(String matricule, String name, String fname, String email_s, String pass1, String pass2, LocalDate bdate) {
        // constructor for teacher
        id = matricule;
        firstName = name;
        lastName = fname;
        email = email_s;
        password = pass1;
        password_confirmation = pass2;
        birth_date = bdate;
    }
    public Data(String user, String pass){
        id = user;
        password = pass;
    }

    public User checkData_login(){
        String error_msg = null;
        User userr = new User();
        if(checkId_login(this.id)){
            // id checked
            if(checkPassword_login(this.password)){
                // password is checked
                //TODO check the database
                Authentication auth = new Authentication();
                User user = auth.auth(this.id, this.password);
                if(user != null) return user;
                else userr.setNeed2passAMSG(auth.getMsg());
            }
            else userr.setNeed2passAMSG("Password must be at least 8 characters long!!");
        }
        else userr.setNeed2passAMSG("Please enter either your id or your email to log in!!");
        return userr;
    }



    public String checkData(){
        String error_msg = null;
        if(checkId(this.id)) {
            // id is checked!
            if (checkName(this.firstName, this.lastName)) {
                // name and last name checked!
                if (checkEmail(this.email)) {
                    // email is checked!
                    if (checkPassword(this.password, this.password_confirmation)) {
                        // password is checked!
                        if (checkDate(this.birth_date)) {
                            // bdate is checked!
                            if (this.level.equals("none")) {
                                // TODO: create user 'teacher' then push to the database
                                User teacher = new User(this.id, this.firstName, this.lastName, this.email, this.password, this.birth_date);
                                Authentication auth = new Authentication();
                                boolean done = auth.sign_up(teacher);
                                if(done) error_msg = "User 'teacher' created successfully";
                                else error_msg = auth.getMsg();
                            } else {
                                // checking group, level and section
                                if (checkClass(this.level, this.section)) {
                                    //checked group, level and section
                                    // TODO: create user 'student' then push to the database
                                    User student = new User(this.id, this.firstName, this.lastName, this.email, this.password, this.birth_date, this.level, this.section);
                                    Authentication auth = new Authentication();
                                    boolean done = auth.sign_up(student);
                                    if(done) error_msg = "User 'student' created successfully";
                                    else error_msg = auth.getMsg();


                                } else error_msg = "Please choose a level, section and a group";

                            }
                        }
                        else error_msg = "You must be at least 18 to register!";
                    }
                    else error_msg = "Passwords dont match, password must be at least 8 chars containing numbers and letters";
                }
                else error_msg = "Please enter a valid usthb email adress";

            }
            else error_msg = "Special characters aren't allowed in the name and last name";
        }
        else error_msg = "Matricule consists of 12 number (students) or 6 (Teacher)";

    return error_msg;
    }

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean checkEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        boolean usthb_valid = emailStr.contains("@usthb.dz") || emailStr.contains("@etu.usthb.dz");
        if (matcher.find() && usthb_valid) return true;
        else return false;
    }
    // name check
    public static boolean checkName(String str1, String str2) {
        String expression = "^[a-zA-Z]+";
        if (str1.matches(expression) && str2.matches(expression)){
            return true;
        }
        return false;
    }

    public static boolean checkPassword(String pass1, String pass2){

        String expression = "^(?=.*[0-9])(?=\\S+$).{8,}$";
        if (pass1.matches(expression) && pass2.matches(expression) && pass1.equals(pass2)){
            return true;
        }
        return false;
    }
    public static boolean checkPassword_login(String pass){
        String expression = "^(?=.*[0-9])(?=\\S+$).{8,}$";
        if (pass.matches(expression)){
            return true;
        }
        return false;

    }

    private boolean checkDate(LocalDate bdate){
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        year = year -18;
        LocalDate bdate2 = LocalDate.of(year, currentDate.getMonthValue(), currentDate.getDayOfMonth());
        if(bdate2.isAfter(bdate)) return true;

        else return false;
    }
    private boolean checkId(String id){
        if (id.length()!=12 && id.length() != 6) return false;
        else {
            for (int i = 0; i < id.length(); i++) {
                if (!Character.isDigit(id.charAt(i))) {
                    return false;
                }
            }
        }
        return true;

    }
    private boolean checkId_login(String id){
        if(checkEmail(id)){
            return true;
        }
        else return checkId(id);
    }
    private boolean checkClass(String level, String section){
        // checking level
        String[] levels = {"L3-ISIL", "L3-ACAD"};
        boolean level_check = false;
        for(int i=0; i<2; i++){
            if (levels[i].equals(level)){
                level_check = true;
                break;
            }
        }
        // checking section
        boolean section_check = false;
        int j=0;
        String[] sections;
        if (level.equals("L3-ISIL")){
            sections = new String[]{"A", "B"};
            j=2;
        }
        else if (level.equals("L3-ACAD")){
            sections = new String[]{"A", "B", "C"};
            j=3;
        }
        else{
            return false;
        }
        for(int i=0; i<j; i++){
            if (sections[i].equals(section)){
                section_check = true;
                break;
            }
        }
        if(level_check && section_check){
            return true;
        }
        else{
            return false;
        }
    }
}

