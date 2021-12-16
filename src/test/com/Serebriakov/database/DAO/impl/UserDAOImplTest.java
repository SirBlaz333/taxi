package com.Serebriakov.database.DAO.impl;

import com.Serebriakov.database.DAO.UserDAO;
import com.Serebriakov.database.DatabaseManager;
import com.Serebriakov.database.SQLQuery;
import com.Serebriakov.database.entity.User;
import com.Serebriakov.exception.DBException;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

class UserDAOImplTest {

    @Test
    void getInstance() {
    }

    @Test
    void getUserByLogin() throws DBException, SQLException, NamingException {
//        System.setProperty("logFile", "log4j2.log");
//        DataSource dataSource = mock(DataSource.class);
//        Context initContext = mock(InitialContext.class);
//        Context envContext = mock(Context.class);
//
//        when(initContext.lookup("java:/comp/env")).thenReturn(envContext);
//        when(envContext.lookup("jdbc/taxi")).thenReturn(dataSource);
//
//        ResultSet rs = mock(ResultSet.class);
//        when(rs.next()).thenReturn(true)
//                .thenReturn(true)
//                .thenReturn(false);
//        when(rs.getInt("id")).thenReturn(1).thenReturn(2);
//
//        PreparedStatement ps = mock(PreparedStatement.class);
//        when(ps.executeQuery()).thenReturn(rs);
//
//        Connection connection = mock(Connection.class);
//        when(connection.prepareStatement(SQLQuery.UserQuery.GET_USER_BY_LOGIN)).thenReturn(ps);
//
//        UserDAO userDAO = mock(UserDAOImpl.class);
//        DatabaseManager dbManager = mock(DatabaseManager.class);
//        //static
//        when(DatabaseManager.getInstance()).thenReturn(dbManager);
//
//        when(userDAO.getUserByLogin("123")).thenCallRealMethod();
//        when(dbManager.getConnection()).thenReturn(connection);
//
//        User user = userDAO.getUserByLogin("123");
//        System.out.println(user.getId());
    }

    @Test
    void getUserById() {
    }

    @Test
    void getDiscount() {
    }

    @Test
    void getUserRole() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void addUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }
}