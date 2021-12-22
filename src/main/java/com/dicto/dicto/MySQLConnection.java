package com.dicto.dicto;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {
    public Connection connect() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String MySQLURL = "jdbc:mysql://db-mysql-lon1-29581-do-user-10316049-0.b.db.ondigitalocean.com:25060/usthb";
        String databaseUserName = "notanadmin";
        String databasePassword = "JlMM0xmXegDWBJDq";
        Connection con = null;
        try {
            con = DriverManager.getConnection(MySQLURL, databaseUserName, databasePassword);
            if (con != null) {
                System.out.println("Database connection is successful !!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
