package com.cybersoft.config;
import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConnection {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/baitapbuoi14",
                    "root",
                    "admin123");
        } catch (Exception e){
            System.out.println("Error Connection: "+e.getMessage());
        }
        return connection;

    }
}
