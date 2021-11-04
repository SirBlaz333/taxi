package com.Serebriakov.database.DAO.impl;

import com.Serebriakov.database.DAO.UserDAO;
import com.Serebriakov.database.DatabaseManager;
import com.Serebriakov.database.SQLQuery;
import com.Serebriakov.entity.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static final String ADD_USER = SQLQuery.UserQuery.ADD_USER;
    private static final String GET_USER_BY_LOGIN = SQLQuery.UserQuery.GET_USER_BY_LOGIN;
    private static final String GET_USER_BY_ID = SQLQuery.UserQuery.GET_USER_BY_ID;
    private static final String GET_ALL_USERS = SQLQuery.UserQuery.GET_ALL_USERS;
    private DatabaseManager dbManager;

    public UserDAOImpl() throws IOException{
        dbManager = DatabaseManager.getInstance();
    }

    @Override
    public User getUserByLogin(String login) throws SQLException {
        User user = null;
        try(Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN)){
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String role = rs.getString("role");
                user = new User(login, id ,role);
            }
        }
        return user;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        User user = null;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                String login = rs.getString("login");
                String role = rs.getString("role");
                user = new User(login, id, role);
            }
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try(Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS)){
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String login = rs.getString("login");
                String role = rs.getString("role");
                User user = new User(login, id, role);
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public void addUser(User user) throws SQLException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER);
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.execute();
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }
}
