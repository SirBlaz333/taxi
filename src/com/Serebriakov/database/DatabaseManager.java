package com.Serebriakov.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {

    private static DatabaseManager dbManager;
    private static String URL;

    static {
        dbManager = null;
    }

    private DatabaseManager() throws IOException{
        Properties properties = new Properties();
        //properties.load(new FileInputStream("connection.properties"));
        //URL = properties.getProperty("url");
        URL = "jdbc:mysql://localhost:3306/taxi?user=root&password=root&serverTimezone=UTC";
    }

    public static DatabaseManager getInstance() throws IOException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(dbManager == null){
            dbManager = new DatabaseManager();
        }
        return dbManager;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
