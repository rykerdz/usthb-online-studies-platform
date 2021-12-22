package com.dicto.dicto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class Authentication {
    private String msg = "";
    private Connection connection;
    Authentication(){
        MySQLConnection conn = new MySQLConnection();
        try {
            connection = conn.connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public User auth(String user, String password) {
        ResultSet result = null;
        PreparedStatement ps = null;
        String teacher_with_id = "SELECT * FROM teacher WHERE id=? and  password=?";
        String student_with_id = "SELECT * FROM student WHERE id=? and password=?";
        String teacher_with_email = "SELECT * FROM teacher WHERE email=? and password=?";
        String student_with_email = "SELECT * From student WHERE email=? and password=?";
        String get_classroom_info = "SELECT * FROM classroom WHERE id=?";
        // id is entered
        if(isId(user)==1){ // teacher_with_id
            try {
                ps = connection.prepareStatement(teacher_with_id);
                ps.setString(1, user);
                ps.setString(2, password);
                result = ps.executeQuery();
                if (result.next()) {
                    String email = result.getString("email");
                    String firstname = result.getString("firstname");
                    String lastname = result.getString("lastname");
                    Date bDay = result.getDate("birthday");
                    LocalDate birthDay = bDay.toLocalDate();

                    // creating user object
                    User teacher = new User(user, firstname, lastname, email, password, birthDay);
                    teacher.setTeacher(true);
                    connection.close();
                    return teacher;
                }
                else {this.msg = "ID or password incorrect!!"; return null;}
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
        if(isId(user)==2){ // student with id
            try {
                ps = connection.prepareStatement(student_with_id);
                ps.setString(1, user);
                ps.setString(2, password);
                result = ps.executeQuery();
                if (result.next()) {
                    String email = result.getString("email");
                    String firstname = result.getString("firstname");
                    String lastname = result.getString("lastname");
                    Date bDay = result.getDate("birthday");
                    int classroom_id = result.getInt("classroom_id");
                    LocalDate birthDay = bDay.toLocalDate();
                    PreparedStatement ps2 = connection.prepareStatement(get_classroom_info);
                    ps2.setInt(1, classroom_id);
                    ResultSet result2 = ps2.executeQuery();
                    if(result2.next()){
                        String level = result2.getString("level");
                        String section = result2.getString("section");
                        User student = new User(user, firstname, lastname, email, password, birthDay, level, section);
                        student.setTeacher(false);
                        connection.close();
                        return student;
                    }
                }
                else {this.msg = "ID or password incorrect!!"; return null;}
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(isId(user)==3){ // teacher_with_email
            try {
                ps = connection.prepareStatement(teacher_with_email);
                ps.setString(1, user);
                ps.setString(2, password);
                result = ps.executeQuery();
                if (result.next()) {
                    String id = result.getString("id");
                    String firstname = result.getString("firstname");
                    String lastname = result.getString("lastname");
                    Date bDay = result.getDate("birthday");
                    LocalDate birthDay = bDay.toLocalDate();

                    // creating user object
                    User teacher = new User(id, firstname, lastname, user, password, birthDay);
                    teacher.setTeacher(true);
                    connection.close();
                    return teacher;
                }
                else {this.msg = "Email or password incorrect!!"; return null;}
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
        if(isId(user)==4){ // student with email
            try {
                ps = connection.prepareStatement(student_with_email);
                ps.setString(1, user);
                ps.setString(2, password);
                result = ps.executeQuery();
                if (result.next()) {
                    String id = result.getString("id");
                    String firstname = result.getString("firstname");
                    String lastname = result.getString("lastname");
                    Date bDay = result.getDate("birthday");
                    int classroom_id = result.getInt("classroom_id");
                    LocalDate birthDay = bDay.toLocalDate();
                    PreparedStatement ps2 = connection.prepareStatement(get_classroom_info);
                    ps2.setInt(1, classroom_id);
                    ResultSet result2 = ps2.executeQuery();
                    if(result2.next()){
                        String level = result2.getString("level");
                        String section = result2.getString("section");
                        User student = new User(id, firstname, lastname, user, password, birthDay, level, section);
                        student.setTeacher(false);
                        connection.close();
                        return student;
                    }
                }
                else {this.msg = "Email or password incorrect!!"; return null;}
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.msg = "Database if off!!";
        return null;
    }

    public boolean sign_up(User user){

        int result;
        PreparedStatement ps = null;
        String check_student_id = "SELECT * FROM student WHERE id=?";
        String check_student_email = "SELECT * FROM student WHERE email=?";
        String check_teacher_id = "SELECT * FROM teacher WHERE id=?";
        String check_teacher_email = "SELECT * FROM teacher WHERE email=?";
        String teacher = "INSERT INTO teacher VALUES(?,?,?,?,?,?)";
        String student = "INSERT INTO student VALUES(?,?,?,?,?,?,?)";
        String getClassroomId = "SELECT * FROM classroom WHERE level=? and section=?";

        if(user.isTeacher()){ // teacher
            try {
                // searching the database for id and email
                PreparedStatement ps2 = connection.prepareStatement(check_teacher_id);
                ps2.setString(1, user.getId());
                ResultSet result1 = ps2.executeQuery();
                if (!result1.next()) {
                    PreparedStatement ps3 = connection.prepareStatement(check_teacher_email);
                    ps3.setString(1, user.getEmail());
                    ResultSet result2 = ps3.executeQuery();
                    if(!result2.next()){
                        ps = connection.prepareStatement(teacher);
                        ps.setString(1, user.getId());
                        ps.setString(2, user.getFirstName());
                        ps.setString(3, user.getLastName());
                        ps.setString(4, user.getEmail());
                        ps.setString(5, user.getPassword());
                        Date date = java.sql.Date.valueOf(user.getBirthDay());
                        ps.setDate(6, date);
                        result = ps.executeUpdate();
                        if (result>0) {
                            connection.close();
                            return true;
                        }

                    }
                    else {this.msg = "This email already exists!"; return false;}

                }
                else {this.msg = "This id already exists!"; return false;}
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
        else { // student
            try {
                // searching the database for id and email
                PreparedStatement pss2 = connection.prepareStatement(check_student_id);
                pss2.setString(1, user.getId());
                ResultSet result1 = pss2.executeQuery();
                if (!result1.next()) {
                    PreparedStatement ps3 = connection.prepareStatement(check_student_email);
                    ps3.setString(1, user.getEmail());
                    ResultSet result3 = ps3.executeQuery();
                    if (!result3.next()) {
                        PreparedStatement ps2 = connection.prepareStatement(getClassroomId);
                        ps2.setString(1, user.getLevel().toUpperCase());
                        ps2.setString(2, user.getSection().toUpperCase());
                        ResultSet result2 = ps2.executeQuery();
                        if (result2.next()) {
                            int id = result2.getInt("id");
                            ps = connection.prepareStatement(student);
                            ps.setString(1, user.getId());
                            ps.setString(2, user.getFirstName());
                            ps.setString(3, user.getLastName());
                            ps.setString(4, user.getEmail());
                            ps.setString(5, user.getPassword());
                            Date date = java.sql.Date.valueOf(user.getBirthDay());
                            ps.setDate(6, date);
                            ps.setInt(7, id);
                            result = ps.executeUpdate();
                            if (result > 0) {
                                connection.close();
                                return true;
                            }
                        }
                    }
                    else {this.msg = "This email already exists!"; return false;}
                }
                else {this.msg = "This id already exists!"; return false;}
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    this.msg = "Database is off!";
    return false;
    }

    public String getMsg(){
        return this.msg;
    }

    private int isId(String id){
        // 1 for teacher id, 2 for student id, 3 for teacher email, 4 for student email
        if (id.length()!=12 && id.length() != 6){
            if(id.contains("@etu.usthb.dz")) return 4;
            else return 3;
        }
        else {
            for (int i = 0; i < id.length(); i++) {
                if (!Character.isDigit(id.charAt(i))) {
                    return 0;
                }
            }
        }
        if(id.length() == 6) return 1;
        else return 2;

    }

    public Hashtable<String, Body> getAnnouncements(int classroom_id){
        Hashtable<String, Body> announs = new Hashtable<String, Body>();

        String getAnnou = "SELECT * FROM announcement WHERE classroom_id=?";
        String getModule = "SELECT * FROM module WHERE id=?";
        String getTeacher = "SELECT * FROM teacher where id=?";
        String teacherName, moduleName;
        try {
            PreparedStatement ps = connection.prepareStatement(getAnnou);
            ResultSet result = null;
            ps.setInt(1,classroom_id);
            result = ps.executeQuery();
            while(result.next()){
                PreparedStatement ps2 = connection.prepareStatement(getModule);
                ps2.setInt(1,result.getInt("module_id"));
                ResultSet rs2 = ps2.executeQuery();
                if(rs2.next()){
                    moduleName = rs2.getString("name");

                }
                else {
                    return null;
                }

                PreparedStatement ps3 = connection.prepareStatement(getTeacher);
                ps3.setInt(1,result.getInt("teacher_id_a"));
                ResultSet rs3 = ps3.executeQuery();
                if(rs3.next()){
                    teacherName = rs3.getString("firstname").toUpperCase() +" "+ rs3.getString("lastname");
                }
                else {
                    return null;
                }

                Timestamp date = result.getTimestamp("time");
                String time = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(date);
                String title = moduleName+": "+teacherName;
                String text = result.getString("body");
                Body body = new Body(text, time);

                announs.put(title, body);




            }
            return announs;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public int getClassroomId(User user){

        String sql = "SELECT * FROM classroom WHERE level=? and section=?";
        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getLevel().toUpperCase());
            ps.setString(2, user.getSection().toUpperCase());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Hashtable<String, ArrayList<Body>> getCourses(int classroom_id){

        Hashtable<String, ArrayList<Body>> courses = new Hashtable<String, ArrayList<Body>>();

        String getCourse = "SELECT * FROM courses WHERE classroom_id_c=? AND endtime>NOW() ORDER BY starttime LIMIT 6";
        String getModule = "SELECT * FROM module WHERE id=?";
        String getTeacher = "SELECT * FROM teacher where id=?";
        String teacherName, moduleName, type;
        try {
            PreparedStatement ps = connection.prepareStatement(getCourse);
            ResultSet result = null;
            ps.setInt(1,classroom_id);
            result = ps.executeQuery();
            while(result.next()){
                PreparedStatement ps2 = connection.prepareStatement(getModule);
                ps2.setInt(1,result.getInt("module_id_c"));
                ResultSet rs2 = ps2.executeQuery();
                if(rs2.next()){
                    moduleName = rs2.getString("name");

                }
                else {
                    return null;
                }

                PreparedStatement ps3 = connection.prepareStatement(getTeacher);
                ps3.setInt(1,result.getInt("teacher_id_c"));
                ResultSet rs3 = ps3.executeQuery();
                if(rs3.next()){
                    teacherName = rs3.getString("firstname").toUpperCase() +" "+ rs3.getString("lastname");
                }
                else {
                    return null;
                }
                type = result.getString("type");
                Timestamp dd1 = result.getTimestamp("starttime");
                Timestamp dd2 = result.getTimestamp("endtime");
                Date dd3 = result.getDate("starttime");
                LocalDate dayed = dd3.toLocalDate();
                //LocalDate dayed2 = dd2.toLocalDate();
                String link = result.getString("meeting_link");
                String time = new SimpleDateFormat("HH:mm").format(dd1);
                String time2 = new SimpleDateFormat("HH:mm").format(dd2);
                String day = " "+dayed.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL))+" ";
                String title = moduleName+": "+teacherName;
                Body body = new Body("  "+time+" - "+time2+"    ", title+" - [ "+type+" ]", link);
                if(courses.containsKey(day)){
                    courses.get(day).add(body);
                }
                else{
                    ArrayList<Body> dayCourses = new ArrayList<Body>();
                    dayCourses.add(body);
                    courses.put(day, dayCourses);
                }

            }

            //System.out.println(courses);
            return courses;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public ObservableList<Filing> getSharedFiles(int classroom_id){
        ObservableList<Filing> files = FXCollections.observableArrayList();
        String getFiles = "SELECT * FROM files WHERE classroom_id=? and type in ('TD', 'COUR', 'TP')";
        String getModule = "SELECT * FROM module WHERE id=?";
        String moduleName, type;
        try {
            PreparedStatement ps = connection.prepareStatement(getFiles);
            ResultSet result = null;
            ps.setInt(1,classroom_id);
            result = ps.executeQuery();
            while(result.next()){
                PreparedStatement ps2 = connection.prepareStatement(getModule);
                ps2.setInt(1,result.getInt("module_id"));
                ResultSet rs2 = ps2.executeQuery();
                if(rs2.next()){
                    moduleName = rs2.getString("name");
                }
                else {
                    return null;
                }

                type = result.getString("type");
                Timestamp time = result.getTimestamp("time");
                String date = new SimpleDateFormat("yyyy/MM/dd - HH:mm").format(time);
                int id = result.getInt("id");
                String filename = result.getString("filename");
                Filing file = new Filing(id, moduleName, type, filename, date);
                files.add(file);

            }

            //System.out.println(courses);
            return files;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
    public ObservableList<Filing> getRecords(int classroom_id){
        ObservableList<Filing> records = FXCollections.observableArrayList();
        String getRecords = "SELECT * FROM records WHERE classroom_id=?";
        String getModule = "SELECT * FROM module WHERE id=?";
        String moduleName, type;
        try {
            PreparedStatement ps = connection.prepareStatement(getRecords);
            ResultSet result = null;
            ps.setInt(1,classroom_id);
            result = ps.executeQuery();
            while(result.next()){
                PreparedStatement ps2 = connection.prepareStatement(getModule);
                ps2.setInt(1,result.getInt("module_id"));
                ResultSet rs2 = ps2.executeQuery();
                if(rs2.next()){
                    moduleName = rs2.getString("name");
                }
                else {
                    return null;
                }

                type = result.getString("type");
                Timestamp time = result.getTimestamp("time");
                String date = new SimpleDateFormat("yyyy/MM/dd - HH:mm").format(time);
                int id = result.getInt("id");
                String filename = result.getString("duration");
                String link = result.getString("link");
                Filing file = new Filing(id, moduleName, type, filename, date);
                file.setLink(link);
                records.add(file);

            }

            //System.out.println(courses);
            return records;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
    public Homework getHomeworkDetails(int homeworkId){
        Homework homework;
        String getHomework = "SELECT * FROM homeworks WHERE id=?";
        String getTeacher = "SELECT * FROM teacher where id=?";
        String getFile = "SELECT * FROM files where id=?";
        String teacherName;
        OneReallySimpleFile file;
        try {
            PreparedStatement ps = connection.prepareStatement(getHomework);
            ResultSet result = null;
            ps.setInt(1,homeworkId);
            result = ps.executeQuery();
            if(result.next()){
                PreparedStatement ps2 = connection.prepareStatement(getTeacher);
                ps2.setInt(1,result.getInt("teacher_id"));
                ResultSet rs2 = ps2.executeQuery();
                if(rs2.next()){
                    teacherName = rs2.getString("firstname").toUpperCase() +" "+ rs2.getString("lastname");
                }
                else {
                    return null;
                }
                PreparedStatement ps3 = connection.prepareStatement(getFile);
                ps3.setInt(1,result.getInt("file"));
                ResultSet rs3 = ps3.executeQuery();
                if(rs3.next()){
                    file = new OneReallySimpleFile(rs3.getInt("id"), rs3.getString("type"), rs3.getString("filename"));
                }
                else {
                    return null;
                }

                String duration = result.getString("duration");
                String title = result.getString("title");
                String body = result.getString("body");
                LocalDateTime published = result.getTimestamp("published").toLocalDateTime();
                String publishedDate = published.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
                LocalDateTime due = result.getTimestamp("duedate").toLocalDateTime();
                String dueDate = due.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
                homework = new Homework(body, teacherName, duration, file, title, publishedDate, dueDate);
                return homework;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public boolean downloadFile(int fileId, String file_name) {

        ResultSet rs = null;
        PreparedStatement ps = null;

        InputStream is;
        OutputStream os;


        try {

            String sql = "SELECT * FROM files WHERE id=?";

            ps = connection.prepareStatement(sql);
            ps.setInt(1, fileId);

            rs = ps.executeQuery();

            while (rs.next()) {
                is = rs.getBinaryStream("file");
                String home = System.getProperty("user.home");
                os = new FileOutputStream(new File(home + "/Downloads/" + file_name));
                byte[] content = new byte[4096];
                int size = 0;
                while ((size = is.read(content)) != -1) {
                    os.write(content, 0, size);
                }
                os.close();
                is.close();
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert ps != null;
                ps.close();
                assert rs != null;
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


        return false;
    }
    public Hashtable<String, ArrayList<Body>> getHomeworks(int classroom_id){
        Hashtable<String, ArrayList<Body>> homeworks = new Hashtable<String, ArrayList<Body>>();
        String getHomeworks = "SELECT * FROM homeworks WHERE classroom_id=? AND duedate>=NOW()";
        String getModule = "SELECT * FROM module WHERE id=?";
        String moduleName;
        try {
            PreparedStatement ps = connection.prepareStatement(getHomeworks);
            ResultSet result = null;
            ps.setInt(1,classroom_id);
            result = ps.executeQuery();
            while(result.next()){
                PreparedStatement ps2 = connection.prepareStatement(getModule);
                ps2.setInt(1,result.getInt("module_id"));
                ResultSet rs2 = ps2.executeQuery();
                if(rs2.next()){
                    moduleName = rs2.getString("name");
                }
                else {
                    return null;
                }
                boolean dueToday = false;
                LocalDate datee = result.getTimestamp("duedate").toLocalDateTime().toLocalDate();
                LocalDate today = LocalDate.now();
                if(datee.equals(today)) dueToday = true;
                System.out.println(dueToday);
                String dueDate = " "+result.getTimestamp("duedate").toLocalDateTime().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL))+" ";
                String duration = result.getString("duration");
                int id = result.getInt("id");
                Body body = new Body(dueDate, duration, id, dueToday);
                if(homeworks.containsKey(moduleName)){
                    homeworks.get(moduleName).add(body);
                }
                else{
                    ArrayList<Body> moduleHomeworks = new ArrayList<Body>();
                    moduleHomeworks.add(body);
                    homeworks.put(moduleName, moduleHomeworks);
                }
            }

            //System.out.println(courses);
            return homeworks;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public boolean isHomeworkDone(int homeworkId, String studentId){
        String isHomeworkDone = "SELECT * FROM homework_status WHERE homework_id=? AND student_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(isHomeworkDone);
            ResultSet result = null;
            ps.setInt(1,homeworkId);
            ps.setString(2,studentId);
            result = ps.executeQuery();
            if(result.next()){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean uploadFileStudent(int homeworkId, String studentId, String FILE_NAME, File file){

        int rs = 0;
        PreparedStatement ps = null;
        boolean done = false;
        String sql = "";

        try {
            sql = "insert into students_files(filename, homework_id, student_id, file) values(?,?,?,?)";

            ps = connection.prepareStatement(sql);
            ps.setString(1, FILE_NAME);
            ps.setInt(2, homeworkId);
            ps.setString(3, studentId);
            FileInputStream FILE_DATA = new FileInputStream(file);
            ps.setBinaryStream(4, (InputStream) FILE_DATA, (int) file.length());

            rs = ps.executeUpdate();
            if (rs > 0) {
                done = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert ps != null;
                ps.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }

        }

        return done;
    }

}


