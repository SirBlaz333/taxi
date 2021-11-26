package com.Serebriakov.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager{

    private static Logger logger = LogManager.getLogger("DBError");
    private static DatabaseManager dbManager;
    private static DataSource ds;
    private static String URL;

    static {
        dbManager = null;
        ds = null;
    }

    private DatabaseManager(){
        try{
            Context initContext = new InitialContext();
            Context envContext = (Context)initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/taxi");
        } catch (NamingException e) {
            String message = "Cannot obtain datasource";
            logger.error(message, e);
            throw new IllegalStateException(message, e);
        }
    }

    public static DatabaseManager getInstance(){
        if(dbManager == null){
            dbManager = new DatabaseManager();
        }
        return dbManager;
    }

    public static void setURL(String url){
        URL = url;
    }

    public Connection getConnection(){
        Connection connection;
        try{
            if(URL != null){
                return DriverManager.getConnection(URL);
            }
            connection = ds.getConnection();
        } catch (SQLException e){
            String message = "Cannot obtain a connection";
            logger.error(message, e);
            throw new IllegalStateException(message, e);
        }
        return connection;
    }
}
