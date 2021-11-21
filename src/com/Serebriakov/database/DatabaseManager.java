package com.Serebriakov.database;

import com.Serebriakov.exception.DBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {

    private static Logger logger = LogManager.getLogger(Thread.currentThread().getName());
    private static DatabaseManager dbManager;
    private static String URL;

    static {
        dbManager = null;
    }

    private DatabaseManager(){
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        Properties properties = new Properties();
        String fileName = rootPath + "connection.properties";
        try {
            properties.load(new FileInputStream(fileName));
        } catch (IOException e) {
            logger.error("Error: Cannot find file '" + fileName +"'");
            logger.debug("'Connection.properties' has been found (" + fileName + ")");
        }
        URL = properties.getProperty("url");
        //URL = "jdbc:mysql://localhost:3306/taxi?user=root&password=root&serverTimezone=UTC";
    }

    public static DatabaseManager getInstance(){
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
