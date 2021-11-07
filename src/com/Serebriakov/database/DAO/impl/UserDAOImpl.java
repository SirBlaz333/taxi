package com.Serebriakov.database.DAO.impl;

import com.Serebriakov.database.DAO.UserDAO;
import com.Serebriakov.database.DatabaseManager;
import com.Serebriakov.database.type.Roles;
import com.Serebriakov.entity.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.Serebriakov.database.SQLQuery.UserQuery.*;

public class UserDAOImpl implements UserDAO {

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
                int roleId = rs.getInt("role_id");
                String password = rs.getString("password");
                String email = rs.getString("email");
                Roles role = getUserRole(roleId);
                user = new User(id, login, password, email, role);
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
                int roleId = rs.getInt("role_id");
                String password = rs.getString("password");
                String email = rs.getString("email");
                Roles role = getUserRole(roleId);
                user = new User(id, login, password, email, role);
            }
        }
        return user;
    }

    @Override
    public Roles getUserRole(int roleId) throws SQLException {
        Roles role = null;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_ROLE)){
            preparedStatement.setInt(1, roleId);
            ResultSet rs = preparedStatement.executeQuery();
            String result = null;
            while(rs.next()){
                result = rs.getString("role");
            }
            if(result!=null){
                role = Roles.getRole(result);
            }
        }
        return role;
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
                int roleId = rs.getInt("role_id");
                String password = rs.getString("password");
                String email = rs.getString("email");
                Roles role = getUserRole(roleId);
                User user = new User(id, login, password, email, role);
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
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.execute();
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }
}
